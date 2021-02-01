package com.dk.vehicle;

import java.util.Date;

import com.dk.vehicle.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.dk.vehicle.model.Vehicle;
import lombok.Data;

@Data
public class VehicleCosts {

    @JsonView(View.Summary.class)
    Vehicle vehicle ;
    @JsonView(View.Summary.class)
    Date from ;
    @JsonView(View.Summary.class)
    Date to;
    @JsonView(View.Summary.class)
    long totalCost;

}
