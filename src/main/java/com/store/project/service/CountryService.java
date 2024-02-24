package com.store.project.service;

import com.store.project.model.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {

    Country saveCountry(Country country);

    Optional<Country> getCountryById(Integer id);

    List<Country> getAllCountries();

    Country updateCountry(Country country);

    void deleteCountryById(Integer id);
}
