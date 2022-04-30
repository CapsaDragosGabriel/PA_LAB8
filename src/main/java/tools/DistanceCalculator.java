package tools;

import databases.City;

import java.lang.Math;

public class DistanceCalculator {
    static public Double distanceBetween(City city1, City city2) {
        double latDif = city1.getLatitude() - city2.getLatitude(), longDif = city1.getLongitutde() - city2.getLongitutde();
        latDif *= latDif;
        longDif *= longDif;
        return Math.sqrt(latDif + longDif);
    }
}
