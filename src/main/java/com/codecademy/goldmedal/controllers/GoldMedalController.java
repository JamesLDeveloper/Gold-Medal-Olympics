package com.codecademy.goldmedal.controllers;

import com.codecademy.goldmedal.model.*;
import com.codecademy.goldmedal.repositories.CountryRepository;
import com.codecademy.goldmedal.repositories.GoldMedalRepository;
import org.apache.commons.text.WordUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/countries")
public class GoldMedalController {
    private final GoldMedalRepository goldMedalRepository;
    private final CountryRepository countryRepository;

    public GoldMedalController(final GoldMedalRepository goldMedalRepository, final CountryRepository countryRepository) {
        this.goldMedalRepository = goldMedalRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping("/all")
    public Iterable<Country> getAllCountries() {
        return this.countryRepository.findAll();
    }


    @GetMapping
    public CountriesResponse getCountries(@RequestParam String sort_by, @RequestParam String ascending) {
        var ascendingOrder = ascending.toLowerCase().equals("y");
        return new CountriesResponse(getCountrySummaries(sort_by.toLowerCase(), ascendingOrder));
    }



    @GetMapping("/{country}")
    public CountryDetailsResponse getCountryDetails(@PathVariable String country) {
        String countryName = WordUtils.capitalizeFully(country);
        return getCountryDetailsResponse(countryName);
    }

    @GetMapping("/{country}/medals")
    public CountryMedalsListResponse getCountryMedalsList(@PathVariable String country, @RequestParam String sort_by, @RequestParam String ascending) {
        String countryName = WordUtils.capitalizeFully(country);
        var ascendingOrder = ascending.toLowerCase().equals("y");
        return getCountryMedalsListResponse(countryName, sort_by.toLowerCase(), ascendingOrder);
    }

    private CountryMedalsListResponse getCountryMedalsListResponse(String countryName, String sortBy, boolean ascendingOrder) {
        List<GoldMedal> medalsList;
        switch (sortBy) {
            case "year":
                medalsList = this.goldMedalRepository.orderByYear(countryName);
                break;
            case "season":
                medalsList = this.goldMedalRepository.orderBySeason(countryName);
                break;
            case "city":
                medalsList = this.goldMedalRepository.orderByCity(countryName);
                break;
            case "name":
                medalsList = this.goldMedalRepository.orderBySport(countryName);
                break;
            case "event":
                medalsList = this.goldMedalRepository.orderByEvent(countryName);
                break;
            default:
                medalsList = new ArrayList<>();
                break;
        }

        return new CountryMedalsListResponse(medalsList);
    }

    private CountryDetailsResponse getCountryDetailsResponse(String countryName) {
        Optional<Country> countryOptional = this.countryRepository.findByCountryName(countryName);
        // TODO: get the country; this repository method should return a java.util.Optional
        if (countryOptional.isEmpty()) {

            return new CountryDetailsResponse(countryName);
        }

        var country = countryOptional.get();
        var goldMedalCount = this.goldMedalRepository.findByCountry(country).size();

                // TODO: get the medal count

        var summerWins = this.goldMedalRepository.findByCountryAndBySeasonOrderByYear(country, "Summer");

                // TODO: get the collection of wins at the Summer Olympics, sorted by year in ascending order
        var numberSummerWins = summerWins.size() > 0 ? summerWins.size() : null;
        var totalSummerEvents = this.goldMedalRepository.findBySeason("Summer").size();

                // TODO: get the total number of events at the Summer Olympics
        var percentageTotalSummerWins = totalSummerEvents != 0 && numberSummerWins != null ? (float) summerWins.size() / totalSummerEvents : null;
        var yearFirstSummerWin = summerWins.size() > 0 ? summerWins.get(0).getYear() : null;

        var winterWins = this.goldMedalRepository.findByCountryAndBySeasonOrderByYear(country, "Winter");
                // TODO: get the collection of wins at the Winter Olympics
        var numberWinterWins = winterWins.size() > 0 ? winterWins.size() : null;
        var totalWinterEvents = this.goldMedalRepository.findBySeason("Winter").size();
                // TODO: get the total number of events at the Winter Olympics, sorted by year in ascending order
        var percentageTotalWinterWins = totalWinterEvents != 0 && numberWinterWins != null ? (float) winterWins.size() / totalWinterEvents : null;
        var yearFirstWinterWin = winterWins.size() > 0 ? winterWins.get(0).getYear() : null;

        var numberEventsWonByFemaleAthletes = this.goldMedalRepository.findByCountryAndByGender(country, "Women").size();
                // TODO: get the number of wins by female athletes
        var numberEventsWonByMaleAthletes =  this.goldMedalRepository.findByCountryAndByGender(country, "Men").size();
    // TODO: get the number of wins by male athletes

        return new CountryDetailsResponse(
                countryName,
                country.getGdp(),
                country.getPopulation(),
                goldMedalCount,
                numberSummerWins,
                percentageTotalSummerWins,
                yearFirstSummerWin,
                numberWinterWins,
                percentageTotalWinterWins,
                yearFirstWinterWin,
                numberEventsWonByFemaleAthletes,
                numberEventsWonByMaleAthletes);
    }

    private List<CountrySummary> getCountrySummaries(String sortBy, boolean ascendingOrder) {
        List<Country> countries;
        switch (sortBy) {
            case "name":
                if (ascendingOrder) {
//                    countries = this.goldMedalRepository.findAllCountriesAndOrderByNameAsc();
                    countries = this.countryRepository.findAllAndOrderByNameAsc();
                } else {
//                    countries = this.goldMedalRepository.findAllCountriesAndOrderByNameDesc();
                    countries = this.countryRepository.findAllAndOrderByNameDesc();
                }
                // TODO: list of countries sorted by name in the given order
                break;
            case "gdp":
                if (ascendingOrder){
//                    countries = this.goldMedalRepository.findAllCountriesAndOrderByGdpAsc();
                    countries = this.countryRepository.findAllAndOrderByGdpAsc();
                } else {
//                    countries = this.goldMedalRepository.findAllCountriesAndOrderByGdpDesc();
                    countries = this.countryRepository.findAllAndOrderByGdpDesc();
                }
            // TODO: list of countries sorted by gdp in the given order
                break;
            case "population":
                if (ascendingOrder){
//                    countries = this.goldMedalRepository.findAllCountriesAndOrderByPopulationAsc();
                    countries = this.countryRepository.findAllAndOrderByPopulationAsc();
                } else {
//                    countries = this.goldMedalRepository.findAllCountriesAndOrderByPopulationDesc();
                    countries = this.countryRepository.findAllAndOrderByPopulationDesc();
                }

            // TODO: list of countries sorted by population in the given order
                break;
            case "medals":
            default:
 //               if (ascendingOrder){
//                    countries = this.goldMedalRepository.findAllCountriesAndOrderByPopulationAsc();
                countries = this.countryRepository.findAllAndOrderByPopulationAsc();
 //               } else {


 //               }

            // TODO: list of countries in any order you choose; for sorting by medal count, additional logic below will handle that
                break;
        }

        var countrySummaries = getCountrySummariesWithMedalCount(countries);

        if (sortBy.equalsIgnoreCase("medals")) {
            countrySummaries = sortByMedalCount(countrySummaries, ascendingOrder);
        }

        return countrySummaries;
    }

    private List<CountrySummary> sortByMedalCount(List<CountrySummary> countrySummaries, boolean ascendingOrder) {
        return countrySummaries.stream()
                .sorted((t1, t2) -> ascendingOrder ?
                        t1.getMedals() - t2.getMedals() :
                        t2.getMedals() - t1.getMedals())
                .collect(Collectors.toList());
    }

    private List<CountrySummary> getCountrySummariesWithMedalCount(List<Country> countries) {
        List<CountrySummary> countrySummaries = new ArrayList<>();
        for (var country : countries) {
            var goldMedalCount = this.goldMedalRepository.findByCountry(country).size();

                    // TODO: get count of medals for the given country
            countrySummaries.add(new CountrySummary(country, goldMedalCount));
        }
        return countrySummaries;
    }
}
