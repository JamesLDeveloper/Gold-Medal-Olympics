package com.codecademy.goldmedal.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codecademy.goldmedal.model.GoldMedal;
import com.codecademy.goldmedal.model.Country;

public interface CountryRepository extends  CrudRepository<Country, Long> {

    Optional<Country> getByName(String countryName);

//    List<Country> findAllCountries();

    //    List<Country> findAllCountriesAndOrderByNameAsc();
    List<Country> getAllByOrderByNameAsc();
    //    List<Country> findAllCountriesAndOrderByNameDesc();
    List<Country> getAllByOrderByNameDesc();

    //    List<Country> findAllCountriesAndOrderByGdpAsc();
    List<Country> getAllByOrderByGdpAsc();
    //    List<Country> findAllCountriesAndOrderByGdpDesc();
    List<Country> getAllByOrderByGdpDesc();
    //    List<Country> findAllCountriesAndOrderByPopulationAsc();
    List<Country> getAllByOrderByPopulationAsc();
    //    List<Country> findAllCountriesAndOrderByPopulationDesc();
    List<Country> getAllByOrderByPopulationDesc();
//    List<GoldMedal> findByCountry(Country country);




}