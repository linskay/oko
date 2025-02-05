package com.upravdom.oko.service;

import com.upravdom.oko.entity.Trainee;
import java.util.List;

public interface TraineeService {
    List<Trainee> getAllTrainees();

    Trainee getTraineeById(Long id);

    Trainee addTrainee(Trainee trainee);

    Trainee updateTrainee(Long id, Trainee trainee);

    void deleteTrainee(Long id);
}