package com.upravdom.oko.service;

import com.upravdom.oko.dto.CityCreateDTO;
import com.upravdom.oko.entity.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();

    City getCityById(Long id);

    City addCity(CityCreateDTO cityCreateDTO);

    City updateCity(Long id, City city);

    void deleteCity(Long id);
}