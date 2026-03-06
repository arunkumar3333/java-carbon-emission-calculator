package com.carbontracker.controller;
import com.carbontracker.entity.Activity;
//import com.carbontracker.entity.TravelActivity;
import com.carbontracker.repository.ActivityRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/activity/add")
public class ActivityController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = Long.parseLong(request.getParameter("userId"));
        String fromCity = request.getParameter("fromCity");
        String toCity = request.getParameter("toCity");
        String vehicleType = request.getParameter("vehicleType");
        int vehicles = Integer.parseInt(request.getParameter("vehicles"));
        double km = Double.parseDouble(request.getParameter("km"));
        double time = Double.parseDouble(request.getParameter("time"));

        //TravelActivity activity = new TravelActivity();
        Activity activity = new Activity();
        activity.setUserId(userId);
        activity.setFromCity(fromCity);
        activity.setToCity(toCity);
        activity.setVehicleType(vehicleType);
        activity.setNumberOfVehicles(vehicles);
        activity.setKmTravelled(km);
        activity.setTravelTimeHrs(time);
        activity.setActivityDate(LocalDate.now());

        ActivityRepository repo = new ActivityRepository();
        repo.saveActivity(activity);

        response.getWriter().println("Activity Saved Successfully");
    }
}