package com.dk.vehicle.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.dk.vehicle.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Data
@Component
public class Vehicle {

    @Id
    @GeneratedValue
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    String registration;

    @Enumerated(EnumType.STRING)
    VehicleCategory category;

    @JsonView(View.Summary.class)
    String make;
    @JsonView(View.Summary.class)
    String model;

    public VehicleCategory getCategory() {
        return category;
    }

    @JsonView(View.Summary.class)
    @Enumerated(EnumType.STRING)
    FuelType fueltype;

    Boolean hired;
    Date hiredDateToFinish;


}
