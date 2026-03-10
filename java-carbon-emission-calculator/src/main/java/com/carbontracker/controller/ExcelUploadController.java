package com.carbontracker.controller;

import com.carbontracker.entity.Activity;
import com.carbontracker.entity.CarbonRecord;
import com.carbontracker.repository.ActivityRepository;
import com.carbontracker.repository.CarbonRepository;
import com.carbontracker.service.CarbonCalculatorService;
import com.carbontracker.util.ExcelReaderUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/uploadExcel")
@MultipartConfig
public class ExcelUploadController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            response.setContentType("text/plain");

            /* Get logged-in userId from session */
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");

            if(userId == null){
                response.getWriter().println("User not logged in");
                return;
            }

            /* Get uploaded file */
            Part filePart = request.getPart("file");

            if (filePart == null || filePart.getSize() == 0) {
                response.getWriter().println("No file uploaded");
                return;
            }

            InputStream inputStream = filePart.getInputStream();

            /* Read Excel rows */
            List<String[]> rows = ExcelReaderUtil.readExcel(inputStream);

            ActivityRepository activityRepo = new ActivityRepository();
            CarbonRepository carbonRepo = new CarbonRepository();

            double totalCarbon = 0;

            /* One batchId per uploaded Excel */
            Long batchId = System.currentTimeMillis();

            for (int i = 0; i < rows.size(); i++) {

                String[] row = rows.get(i);

                /* Skip header */
                if (i == 0) {
                    continue;
                }

                if (row.length < 6) {
                    continue;
                }

                String fromCity = row[0];
                String toCity = row[1];
                String vehicleType = row[2];

                int vehicles = Integer.parseInt(row[3]);
                double km = Double.parseDouble(row[4]);
                double time = Double.parseDouble(row[5]);

                /* Save Activity */

                Activity activity = new Activity();
                activity.setUserId(userId);
                activity.setFromCity(fromCity);
                activity.setToCity(toCity);
                activity.setVehicleType(vehicleType);
                activity.setNumberOfVehicles(vehicles);
                activity.setKmTravelled(km);
                activity.setTravelTimeHrs(time);
                activity.setActivityDate(LocalDate.now());

                Long activityId = activityRepo.saveActivity(activity);

                /* Carbon calculation */

                double factor = CarbonCalculatorService.getFactor(vehicleType);
                double co2 = CarbonCalculatorService.calculateCarbon(vehicleType, km, vehicles);

                totalCarbon += co2;

                /* Save Carbon Record */

                CarbonRecord record = new CarbonRecord();
                record.setUserId(userId);
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
            response.getWriter().println("Error processing Excel file");
        }
    }
}