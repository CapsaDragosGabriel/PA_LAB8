package databases;

import lombok.Data;

@Data
public class City {
    String name;
    String country;
    Double latitude;
    Double longitutde;
    Boolean isCapital = true;

    public City() {
    }

    public City(String name, String country, double latitude, double longitude, boolean capital) {
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitutde = longitude;
        this.isCapital = capital;
    }
}
