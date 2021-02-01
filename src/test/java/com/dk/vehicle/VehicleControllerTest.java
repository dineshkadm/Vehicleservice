package com.dk.vehicle;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc

public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetAllVehicleThatReturnsListOfVehiclesJson() throws Exception {

//        JSONObject actual = new JSONObject("[{\"id\":1,\"registration\":\"YH14 AKL\",\"make\":\"BMW\",\"model\":\"X2\",\"fueltype\":\"Petrol\"}]");
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].registration").isString());

    }

    @Test
    public void testCalulateCostOfSpecificVehcileThatReturnsCostInInteger() throws Exception {

        Date currentDate = new Date();

        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        String strDate = "23.01.22";
        String toDate = "25.01.22";

        mvc.perform(MockMvcRequestBuilders.get("/calculate")
                .param("vehicleId","1").param("fromDate",strDate).param("toDate",toDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(100));
//                .andExpect(jsonPath("$.[0].registration").isString());
//                .andExpect(content().string(equalTo("100")));
    }
}


