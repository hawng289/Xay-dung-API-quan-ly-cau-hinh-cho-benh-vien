package com.example.demo.ris.controller;

import com.example.demo.ris.module.HospitalConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @PostMapping ("/create")
    public HospitalConfig create(@RequestBody HospitalConfig hospitalConfig) {

        return null;
    }
}
