package com.upravdom.oko.repositoriy;

import com.upravdom.oko.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}