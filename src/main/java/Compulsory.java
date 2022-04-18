import databases.ContinentDAO;
import databases.CountryDAO;
import databases.Database;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class Compulsory {
    public static void main(String[] args) {
        try {
            var continents = new ContinentDAO();
            continents.create("Europe");

            var countries = new CountryDAO();
            int europeId = continents.findByName("Europe");
            countries.create("Romania", europeId);
            countries.create("Ukraine", europeId);
            Database.getConnection().commit();

            countries.countriesOnContinent(1);

            System.out.println(countries.findByName("Romania"));
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }

    }

}
