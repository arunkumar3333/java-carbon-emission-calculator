package com.carbontracker.service;

import java.util.HashMap;
import java.util.Map;

public class CarbonCalculatorService {

    private static Map<String, Double> emissionFactors = new HashMap<>();

    static {
        emissionFactors.put("car", 0.21);
        emissionFactors.put("bike", 0.05);
        emissionFactors.put("bus", 0.10);
        emissionFactors.put("truck", 0.30);
        emissionFactors.put("auto", 0.12);
        emissionFactors.put("van", 0.25);
    }

    public static double getFactor(String vehicleType) {

        return emissionFactors.get(vehicleType.toLowerCase());

    }

    public static double calculateCarbon(String vehicleType, double km, int vehicles) {

        double factor = emissionFactors.get(vehicleType.toLowerCase());

        return km * factor * vehicles;

    }
}