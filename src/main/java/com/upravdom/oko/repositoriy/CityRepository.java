package com.upravdom.oko.repositoriy;

import com.upravdom.oko.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}