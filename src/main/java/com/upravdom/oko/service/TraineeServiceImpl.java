package com.upravdom.oko.service;

import com.upravdom.oko.model.City;
import com.upravdom.oko.model.Trainee;
import com.upravdom.oko.repositoriy.CityRepository;
import com.upravdom.oko.repositoriy.TraineeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {
    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Trainee createTrainee(String name, String lastName, Integer cityId) {
        logger.info("Attempting to create new trainee with name: {}, lastName: {}, cityId: {}", name, lastName, cityId);
        City city = null;
        try{
            city = cityRepository.findById(cityId)
                    .orElseThrow(() -> new IllegalArgumentException("City not found with id: " + cityId));
        }catch (IllegalArgumentException e){
            logger.error("Error creating trainee: {}", e.getMessage());
            throw e;
        }

        Trainee trainee = new Trainee(lastName, name, city);
        Trainee savedTrainee = traineeRepository.save(trainee);
        logger.info("Successfully created trainee with id: {}", savedTrainee.getId());

        return savedTrainee;
    }
}
