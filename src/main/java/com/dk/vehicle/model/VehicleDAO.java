package com.dk.vehicle.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public class VehicleDAO {


    public static final int MAX_RESULTS = 10;
    @PersistenceContext
    private EntityManager em;


    @Transactional
    public void add(Vehicle vehicle) {
        em.persist(vehicle);
    }


    public List<Vehicle> findAllAvailableVehicles(@Param("fromDate") Date fromDate) {
        return em.createQuery(
                "SELECT v FROM Vehicle v WHERE v.hired = false and (v.hiredDateToFinish < :fromDate or v.hiredDateToFinish =null)")
                .setMaxResults(MAX_RESULTS)
                .setParameter("fromDate",fromDate)
                .getResultList();
    }

    public List<Vehicle> findAllAvailableVehicles(@Param("fromDate") Date fromDate,final long vehicleId) {
        return em.createQuery(
                "SELECT v FROM Vehicle v WHERE v.hired = false " +
                        "and (v.hiredDateToFinish < :fromDate or v.hiredDateToFinish =null) " +
                        "and v.id =:vehicleId")
                .setMaxResults(MAX_RESULTS)
                .setParameter("fromDate",fromDate)
                .setParameter("vehicleId",vehicleId)
                .getResultList();
    }
}
