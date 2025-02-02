package com.upravdom.oko.service;

import com.upravdom.oko.model.Trainee;

public interface TraineeService {
    Trainee createTrainee(String name, String lastName, Integer cityId);
}
