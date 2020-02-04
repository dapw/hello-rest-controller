package com.galvanize.hellorestcontroller.controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.galvanize.hellorestcontroller.entities.Person;

@RestController
@RequestMapping("/hello")
public class HelloRestController {

    @GetMapping
    public Person get(@RequestParam String name,
                      @RequestParam @DateTimeFormat(pattern = "M/d/yyyy") LocalDate dateRegistered,
                      @RequestParam String email,
                      @RequestParam String address) {


        Person person = new Person(name, dateRegistered, email, address);

        System.out.println(String.format("GET: %s", person));

        return person;
    }


    @PostMapping
    public Person post(@RequestBody Person person) {

        System.out.println(String.format("POST: %s", person));

        return person;
    }
}