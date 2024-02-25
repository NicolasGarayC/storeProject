package com.store.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.store.project.model.City;
import com.store.project.service.CityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City savedCity = cityService.saveCity(city);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id) {
        Optional<City> city = cityService.getCityById(id);
        return city.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Integer id, @RequestBody City cityDetails) {
        cityDetails.setId(id);
        City updatedCity = cityService.updateCity(cityDetails);
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable Integer id) {
        cityService.deleteCityById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
