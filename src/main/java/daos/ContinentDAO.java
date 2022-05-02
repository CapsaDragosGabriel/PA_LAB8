package daos;

import databases.Database;

import java.sql.*;

public class ContinentDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        if (findByName(name) == null) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into continents (name) values (?)")) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            }
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from continents where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id_find) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from continents where id=" + id_find + "")) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

}
