@WebServlet("/uploadExcel")
@MultipartConfig
public class ExcelUploadController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {

            response.setContentType("text/plain");

            Part filePart = request.getPart("file");
            InputStream inputStream = filePart.getInputStream();

            List<String[]> rows = ExcelReaderUtil.readExcel(inputStream);

            ActivityRepository activityRepo = new ActivityRepository();
            CarbonRepository carbonRepo = new CarbonRepository();

            double totalCarbon = 0;

            /* ONE batchId per CSV */
            Long batchId = System.currentTimeMillis();

            for (String[] row : rows) {

                String fromCity = row[0];
                String toCity = row[1];
                String vehicleType = row[2];
                int vehicles = Integer.parseInt(row[3]);
                double km = Double.parseDouble(row[4]);
                double time = Double.parseDouble(row[5]);

                Activity activity = new Activity();

                activity.setUserId(1L);
                activity.setFromCity(fromCity);
                activity.setToCity(toCity);
                activity.setVehicleType(vehicleType);
                activity.setNumberOfVehicles(vehicles);
                activity.setKmTravelled(km);
                activity.setTravelTimeHrs(time);
                activity.setActivityDate(LocalDate.now());

                Long activityId = activityRepo.saveActivity(activity);

                double factor = CarbonCalculatorService.getFactor(vehicleType);

                double co2 = CarbonCalculatorService.calculateCarbon(vehicleType, km, vehicles);

                totalCarbon += co2;

                CarbonRecord record = new CarbonRecord();

                record.setUserId(1L);
                record.setActivityId(activityId);
                record.setEmissionFactor(factor);
                record.setCo2Emission(co2);
                record.setCalculationDate(LocalDate.now());
                record.setBatchId(batchId);

                carbonRepo.saveCarbonRecord(record);
            }

            inputStream.close();

            response.getWriter().println("Total Carbon Emission = " + totalCarbon + " kg CO2");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}