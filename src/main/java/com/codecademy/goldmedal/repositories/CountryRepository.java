package com.codecademy.goldmedal.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codecademy.goldmedal.model.GoldMedal;
import com.codecademy.goldmedal.model.Country;

public interface CountryRepository extends  CrudRepository<Country, Long> {

    Optional<Country> findByCountryName(String countryName);

//    List<Country> findAllCountries();

    //    List<Country> findAllCountriesAndOrderByNameAsc();
    List<Country> findAllAndOrderByNameAsc();
    //    List<Country> findAllCountriesAndOrderByNameDesc();
    List<Country> findAllAndOrderByNameDesc();

    //    List<Country> findAllCountriesAndOrderByGdpAsc();
    List<Country> findAllAndOrderByGdpAsc();
    //    List<Country> findAllCountriesAndOrderByGdpDesc();
    List<Country> findAllAndOrderByGdpDesc();
    //    List<Country> findAllCountriesAndOrderByPopulationAsc();
    List<Country> findAllAndOrderByPopulationAsc();
    //    List<Country> findAllCountriesAndOrderByPopulationDesc();
    List<Country> findAllAndOrderByPopulationDesc();
//    List<GoldMedal> findByCountry(Country country);




}