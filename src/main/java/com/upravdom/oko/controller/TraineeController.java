package com.upravdom.oko.controller;

import com.upravdom.oko.model.Trainee;
import com.upravdom.oko.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee addTrainee(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        String lastName = (String) requestBody.get("lastName");
        Integer cityId = (Integer) requestBody.get("cityId");

        return traineeService.createTrainee(name, lastName, cityId);
    }
}
