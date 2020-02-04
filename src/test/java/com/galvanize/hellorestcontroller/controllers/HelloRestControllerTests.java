package com.galvanize.hellorestcontroller.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.galvanize.hellorestcontroller.entities.Person;

@SpringBootTest
@AutoConfigureMockMvc
class HelloRestControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void testGet() throws Exception {
        String url = "/hello?name=David&dateRegistered=1/1/1975&email=none@none.net&address=1111 Main St. Richmond VA, 23223";
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("none@none.net")))
                .andExpect(jsonPath("$.yearsRegistered").value(45));
    }

    @Test
    void testPost() throws Exception {
        String json = "{\"name\":\"David\",\"dateRegistered\":\"1/1/1975\",\"email\":\"none@none.net\",\"address\":\"1111 Main St. Richmond VA, 23223\"}";
        mvc.perform(post("/hello")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("none@none.net")))
                .andExpect(jsonPath("$.yearsRegistered").value(45)
            );
    }

    @Test
    void testYearsRegistered() {

        long years = 10;

        HelloRestController controller = new HelloRestController();

        Person p = controller.get("David", getRegistrationDate(years), "none@none.net", "1111 Main St. Richmond VA, 23223");

        assertEquals(p.getYearsRegistered(), years);
    }

    private LocalDate getRegistrationDate(long years) {

        return LocalDate.now().minus(years, ChronoUnit.YEARS);
    }
}