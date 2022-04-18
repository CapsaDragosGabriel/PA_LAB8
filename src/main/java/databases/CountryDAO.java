package databases;

import java.sql.*;

public class CountryDAO {
    public void create(String name,Integer continentId) throws SQLException {
        Connection con = Database.getConnection();
        if (findByName(name)==null)
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (name,code) values (?,?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2,continentId);


            pstmt.executeUpdate();
        }
    }
    public void countriesOnContinent(int code) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from countries where code=" + code +"")) {
            while (rs.next())
            {
                System.out.println(rs.getString(1));
            }
        }

    }


    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from countries where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(int id_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from countries where id=id_find")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
    public String findByContinentId(int id_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from countries where continentId=id_find")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
