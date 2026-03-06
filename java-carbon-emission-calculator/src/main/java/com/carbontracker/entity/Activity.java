package com.carbontracker.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "travel_activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
    private String toCity;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "number_of_vehicles")
    private int numberOfVehicles;

    @Column(name = "km_travelled")
    private double kmTravelled;

    @Column(name = "travel_time_hrs")
    private double travelTimeHrs;

    @Column(name = "activity_date")
    private LocalDate activityDate;

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public double getKmTravelled() {
        return kmTravelled;
    }

    public void setKmTravelled(double kmTravelled) {
        this.kmTravelled = kmTravelled;
    }

    public double getTravelTimeHrs() {
        return travelTimeHrs;
    }

    public void setTravelTimeHrs(double travelTimeHrs) {
        this.travelTimeHrs = travelTimeHrs;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }
}