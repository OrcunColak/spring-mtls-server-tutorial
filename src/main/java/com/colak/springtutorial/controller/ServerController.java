package com.colak.springtutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    // https://localhost:8080/connect
    @GetMapping("/connect")
    public String get() {
        return "Successfully connected!";
    }
}
