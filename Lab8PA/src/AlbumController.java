import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    void create(String name, int artistId, int releaseYear) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String statement = "insert into albums(name,artist_id,release_year) values ('" + name + "'," + artistId + "," + releaseYear + ")";
        System.out.println(statement);
        stmt.executeUpdate(statement);
    }

    List<Album> findByArtist(int artistId) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from albums where artist_id = " + artistId);
        List<Album> albums = new ArrayList<>();
        while (rs.next()) {
            Album album = new Album(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            albums.add(album);
        }
        return albums;
    }

    Album findById(int id) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from albums where id = " + id);
        if (rs.next()) {
            Album album = new Album(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(3));
            return album;
        } else {
            return null;
        }
    }

    Album findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from albums where name= '" + name + "'");
        if (rs.next()) {
            Album album = new Album(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(3));
            return album;
        } else {
            return null;
        }
    }
}

