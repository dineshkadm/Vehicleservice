package com.dk.vehicle.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.dk.vehicle.VehicleCosts;
import com.dk.vehicle.model.Vehicle;
import com.dk.vehicle.model.VehicleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleInventory {

    @Autowired
    VehicleDAO vehicleDAO;

    public List<Vehicle> findVehicles() {
        return vehicleDAO.findAllAvailableVehicles(new Date());
    }

    public VehicleCosts findVehicles(final int vehicleId, final Date fromDate, final Date toDate) {

        long diff = TimeUnit.DAYS.convert(Math.abs(toDate.getTime() - fromDate.getTime()), TimeUnit.MILLISECONDS);

        List<Vehicle> availableVehicles = vehicleDAO.findAllAvailableVehicles(fromDate,vehicleId);
        VehicleCosts vehicleCosts = new VehicleCosts();
        vehicleCosts.setVehicle(availableVehicles.get(0));
        vehicleCosts.setFrom(fromDate);
        vehicleCosts.setTo(toDate);

        vehicleCosts.setTotalCost(availableVehicles.stream()
                .map(s -> s.getCategory().getPrice())
                .collect(Collectors.summingInt(Integer::intValue)) * diff);
        return vehicleCosts;

    }

}
