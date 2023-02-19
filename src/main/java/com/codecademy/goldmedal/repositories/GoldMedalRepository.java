package com.codecademy.goldmedal.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codecademy.goldmedal.model.GoldMedal;
import com.codecademy.goldmedal.model.Country;

public interface GoldMedalRepository extends  CrudRepository<GoldMedal, Country> {

//    List<GoldMedal> findByCountryName(String countryName);
    Optional<Country> findByCountryName(String countryName);

//    List<Country> findAllCountries();

//    List<Country> findAllCountriesAndOrderByNameAsc();
    List<Country> findAllAndOrderByName();
//    List<Country> findAllCountriesAndOrderByNameDesc();
    List<Country> findAllAndOrderByNameDesc();

//    List<Country> findAllCountriesAndOrderByGdpAsc();
    List<Country> findAllAndOrderByGdp();
//    List<Country> findAllCountriesAndOrderByGdpDesc();
    List<Country> findAllAndOrderByGdpDesc();
//    List<Country> findAllCountriesAndOrderByPopulationAsc();
    List<Country> findAllAndOrderByPopulation();
//    List<Country> findAllCountriesAndOrderByPopulationDesc();
    List<Country> findAllAndOrderByPopulationDesc();
//    List<GoldMedal> findByCountry(Country country);
    List<GoldMedal> findByCountry(Country country);

   List<GoldMedal> findByYear(String countryName);
   List<GoldMedal> orderByYear(String countryName);
    List<GoldMedal> orderBySeason(String countryName);
    List<GoldMedal> findBySeason(String season);
    List<GoldMedal> findByCountryAndBySeasonOrderByYear(Country country, String season);
    List<GoldMedal> orderByCity(String countryName);
    List<GoldMedal> orderBySport(String countryName);
    List<GoldMedal> orderByEvent(String countryName);

    List<GoldMedal> findByCountryAndByGender(Country country, String gender);

}
