package com.carbontracker.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderUtil {

    public static List<String[]> readExcel(InputStream inputStream) throws Exception {

        List<String[]> rows = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {

            // Skip header row
            if (row.getRowNum() == 0) {
                continue;
            }

            String fromCity = row.getCell(0).getStringCellValue();
            String toCity = row.getCell(1).getStringCellValue();
            String vehicleType = row.getCell(2).getStringCellValue();

            int numberOfVehicles = (int) row.getCell(3).getNumericCellValue();
            double kmTravelled = row.getCell(4).getNumericCellValue();
            double travelTime = row.getCell(5).getNumericCellValue();

            String[] data = new String[6];

            data[0] = fromCity;
            data[1] = toCity;
            data[2] = vehicleType;
            data[3] = String.valueOf(numberOfVehicles);
            data[4] = String.valueOf(kmTravelled);
            data[5] = String.valueOf(travelTime);

            rows.add(data);
        }

        workbook.close();

        return rows;
    }
}