package com.upravdom.oko.controller;

import com.upravdom.oko.exeption.TraineeNotFoundException;
import com.upravdom.oko.model.City;
import com.upravdom.oko.repositoriy.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> listCities() {
        return cityRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City addCity(@RequestBody City city) {
        return cityRepository.save(city);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Integer id) {
        if(!cityRepository.existsById(id)) {
            throw new TraineeNotFoundException("City not found with id: " + id);
        }
        cityRepository.deleteById(id);
    }
}