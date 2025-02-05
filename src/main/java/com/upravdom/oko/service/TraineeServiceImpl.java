package com.upravdom.oko.service;

import com.upravdom.oko.entity.Trainee;
import com.upravdom.oko.service.TraineeService;
import com.upravdom.oko.repositoriy.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TraineeServiceImpl implements TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;

    @Override
    public List<Trainee> getAllTrainees() {
        return traineeRepository.findAll();
    }

    @Override
    public Trainee getTraineeById(Long id) {
        return traineeRepository.findById(id).orElseThrow(() -> new RuntimeException("Trainee not found"));
    }

    @Override
    public Trainee addTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }

    @Override
    public Trainee updateTrainee(Long id, Trainee trainee) {
        trainee.setId(id);
        return traineeRepository.save(trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeRepository.deleteById(id);
    }
}