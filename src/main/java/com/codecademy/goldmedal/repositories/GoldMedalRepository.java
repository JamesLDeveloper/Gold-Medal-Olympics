package com.codecademy.goldmedal.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codecademy.goldmedal.model.GoldMedal;
import com.codecademy.goldmedal.model.Country;

public interface GoldMedalRepository extends  CrudRepository<GoldMedal, Long> {

//    List<GoldMedal> findByCountryName(String countryName);
//    Optional<Country> findByCountryName(String countryName);

//    List<Country> findAllCountries();

//    List<Country> findAllCountriesAndOrderByNameAsc();
//    List<Country> findAllAndOrderByName();
//    List<Country> findAllCountriesAndOrderByNameDesc();
//    List<Country> findAllAndOrderByNameDesc();

//    List<Country> findAllCountriesAndOrderByGdpAsc();
//    List<Country> findAllAndOrderByGdp();
//    List<Country> findAllCountriesAndOrderByGdpDesc();
 //   List<Country> findAllAndOrderByGdpDesc();
//    List<Country> findAllCountriesAndOrderByPopulationAsc();
//    List<Country> findAllAndOrderByPopulation();
//    List<Country> findAllCountriesAndOrderByPopulationDesc();
//    List<Country> findAllAndOrderByPopulationDesc();
//    List<GoldMedal> findByCountry(Country country);
    List<GoldMedal> findByCountry(Country country);

    int countByCountry(String country);
    int countBySeason(String season);
    int countByCountryAndGender(String country, String gender);

   List<GoldMedal> findByYear(String countryName);
   List<GoldMedal> getByCountryOrderByYearAsc(String country);
    List<GoldMedal> getByCountryOrderByYearDesc(String country);
    List<GoldMedal> getByCountryOrderBySeasonAsc(String country);
    List<GoldMedal> getByCountryOrderBySeasonDesc(String country);
//    List<GoldMedal> findBySeason(String season);
List<GoldMedal> getByCountryOrderByCityAsc(String country);
    List<GoldMedal> getByCountryOrderByCityDesc(String country);

    List<GoldMedal> getByCountryOrderByNameAsc(String country);
    List<GoldMedal> getByCountryOrderByNameDesc(String country);
    List<GoldMedal> getByCountryOrderByEventAsc(String country);
    List<GoldMedal> getByCountryOrderByEventDesc(String country);  

    List<GoldMedal> getByCountryAndSeasonOrderByYearAsc(String country, String season);
    List<GoldMedal> getByCountryAndSeasonOrderByYearDesc(String country, String season);

//    List<GoldMedal> orderBySport(String countryName);
//    List<GoldMedal> orderByEvent(String country);

//    List<GoldMedal> findByCountryAndByGender(Country country, String gender);

}
