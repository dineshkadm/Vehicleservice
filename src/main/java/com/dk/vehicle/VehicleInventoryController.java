package com.dk.vehicle;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.dk.vehicle.model.Vehicle;
import com.dk.vehicle.service.VehicleInventory;
import com.dk.vehicle.view.View;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class VehicleInventoryController {

@Autowired
VehicleInventory vehicleInventory;

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    @GetMapping("/")
    public List<Vehicle> index() {
        log.debug("received request: " );
        return vehicleInventory.findVehicles();
    }

    @GetMapping("/calculate")
    public VehicleCosts calculate(@RequestParam int vehicleId,
                     @RequestParam("fromDate") @DateTimeFormat(pattern = "dd.mm.yyyy") Date fromDate,
                     @RequestParam("toDate") @DateTimeFormat(pattern = "dd.mm.yyyy") Date toDate) {

        log.debug("received calculate request: " + vehicleId);
        return vehicleInventory.findVehicles(vehicleId,fromDate,toDate);
    }

}