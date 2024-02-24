package com.store.project.service;

import com.store.project.model.City;
import java.util.List;
import java.util.Optional;

public interface CityService {

    City saveCity(City city);

    Optional<City> getCityById(Integer id);

    List<City> getAllCities();

    City updateCity(City city);

    void deleteCityById(Integer id);
}
