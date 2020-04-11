import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtistController {


    void create(String name, String country) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String statement = "insert into artists(name,country) values ('" + name + "','" + country + "')";
        System.out.println(statement);
        stmt.executeUpdate(statement);
    }

    Artist findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from artists where name = '" + name + "'");
        if (rs.next()) {
            Artist artist = new Artist(rs.getInt(1), rs.getString(2), rs.getString(3));
            return artist;
        } else {
            return null;
        }
    }

    Artist findById(Integer id) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from artists where id = " + id);
        if (rs.next()) {
            Artist artist = new Artist(rs.getInt(1), rs.getString(2), rs.getString(3));
            return artist;
        } else {
            return null;
        }

    }
}
