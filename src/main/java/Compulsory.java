import daos.CityDAO;
import daos.ContinentDAO;
import daos.CountryDAO;
import databases.*;
import model.City;
import tools.DistanceGFG;
import tools.Loader;

import java.sql.SQLException;
import java.util.Random;

public class Compulsory {
    int maxCities = 0;

    public void populateDbs() {
        try {
            var continents = new ContinentDAO();
            continents.create("Europe");

            var countries = new CountryDAO();
            int europeId = continents.findByName("Europe");
            countries.create("Romania", europeId);
            countries.create("Ukraine", europeId);
            var cities = new CityDAO();
            cities.create("Bucharest", "Romania", true,
                    44.43333333, 26.1);
            cities.create("Bacau", "Romania", false,
                    46.5529, 26.9151);
            Database.getConnection().commit();

            countries.countriesOnContinent(1);

            System.out.println(countries.findByName("Romania"));
            System.out.println(cities.findByCountry("Romania"));
            System.out.println(continents.findById(1));
            System.out.println(cities.findById(1));

            Loader.loadCities(cities);
            cities.create("Test", "Test", true,
                    420.0, 420.0);
            Database.getConnection().commit();
            maxCities = cities.noOfCities();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }

    }

    public void getDistances(int id1, int id2) {
        try {
            var cities = new CityDAO();

            City tempCity1 = new City(cities.findById(id1), cities.countryById(id1), (double) cities.latitudeById(id1), (double) cities.longitudeById(id1), cities.isCapitalById(id1));
            City tempCity2 = new City(cities.findById(id2), cities.countryById(id2), (double) cities.latitudeById(id2), (double) cities.longitudeById(id2), cities.isCapitalById(id2));
            printDistance(tempCity1, tempCity2, DistanceGFG.distance(tempCity1, tempCity2));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void printDistance(City city1, City city2, Double distance) {
        System.out.println("Distanta de la orasul " + city1.getName() + " la orasul " + city2.getName() + " este de " + distance + " kilometrii.");
    }

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void main(String[] args) {
        Compulsory obj = new Compulsory();
        obj.populateDbs();
        obj.getDistances(1, 2);

        int noOfRandomPairsOfCities = 5;
        while (noOfRandomPairsOfCities > 0) {
            noOfRandomPairsOfCities--;
            int firstCityId = obj.getRandomNumber(1, obj.maxCities);
            int secondCityId = obj.getRandomNumber(1, obj.maxCities);
            while (secondCityId == firstCityId) secondCityId = obj.getRandomNumber(1, obj.maxCities);
            obj.getDistances(firstCityId, secondCityId);
        }
            Database.closeConnection();


    }

}
