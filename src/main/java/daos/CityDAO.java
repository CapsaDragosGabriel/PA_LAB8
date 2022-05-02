package daos;

import databases.Database;

import java.sql.*;

public class CityDAO {
    public void create(String name, String country, boolean isCapital, Double latitude, Double longitude) throws SQLException {
        Connection con = Database.getConnection();
        if (findByName(name) == null)
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into cities (name,country,capital,latitude,longitude) values (?,?,?,?,?)")) {
                pstmt.setString(1, name);
                pstmt.setString(2, country);
                pstmt.setBoolean(3, isCapital);
                pstmt.setDouble(4, latitude);
                pstmt.setDouble(5, longitude);

                pstmt.executeUpdate();
            }
    }


    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from cities where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public Integer noOfCities() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select max(id) from cities")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from cities where id=" + id_find + "")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public String countryById(int id_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select country from cities where id=" + id_find + "")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public boolean isCapitalById(int id_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select capital from cities where id=" + id_find + "")) {
            return rs.next() ? (rs.getString(1).equals("true") ? true : false) : null;
        }
    }

    public Double latitudeById(int id_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select latitude from cities where id=" + id_find + "")) {
            return rs.next() ? rs.getDouble(1) : null;
        }
    }

    public Double longitudeById(int id_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select longitude from cities where id=" + id_find + "")) {
            return rs.next() ? rs.getDouble(1) : null;
        }
    }

    public String findByCountry(String country_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from cities where country='" + country_find + "'")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
