package com.upravdom.oko.service;



import com.upravdom.oko.dto.CityCreateDTO;
import com.upravdom.oko.entity.City;
import com.upravdom.oko.repositoriy.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
    }

    @Override
    public City addCity(CityCreateDTO cityCreateDTO) {
        City city = new City();
        city.setCityName(cityCreateDTO.getCityName());
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(Long id, City city) {
        city.setId(id);
        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}