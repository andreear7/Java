import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Stream;


public class ChartController {
    void createChart(String name) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String statement = "insert into chart(name) values ('" + name + "')";
        System.out.println(statement);
        stmt.executeUpdate(statement);
    }

    void addMusic(Chart chart, Album album, Integer order) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        String statement = "insert into charts(id_chart,id_album,order_album) values (" + chart.getId() + "," + album.getId() + "," + order + ")";
        System.out.println(statement);
        stmt.executeUpdate(statement);
    }

    void viewRanking() throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        List<Integer> albumIds = new ArrayList<>();
        List<Integer> chartIds = new ArrayList<>();
        List<Integer> orders = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select * from charts");
        while (rs.next()) {
            Integer albumId = rs.getInt(2);
            Integer chartIs = rs.getInt(1);
            Integer order = rs.getInt(3);
            albumIds.add(albumId);
            chartIds.add(chartIs);
            orders.add(order);
        }
        int index;
        List<Integer> artistsIds = new ArrayList<>();
        for (index = 0; index < albumIds.size(); index++) {
            AlbumController albumC = new AlbumController();
            artistsIds.add(albumC.findById(albumIds.get(index)).getArtistId());
            //System.out.println(albumC.findById(albumIds.get(index)).getArtistId());
        }
        Map<Integer, Integer> artistRank = new HashMap<>();
        for (index = 0; index < albumIds.size(); index++) {
            //Integer value=artistRank.get(artistsIds.get(index));
            if (artistRank.containsKey(artistsIds.get(index))) {
                // System.out.println(artistRank.get(artistsIds.get(index))+orders.get(index));
                artistRank.put(artistsIds.get(index), artistRank.get(artistsIds.get(index)) + orders.get(index));
            } else {
                artistRank.put(artistsIds.get(index), orders.get(index));
            }
        }
/*
        Map<Artist,Integer> artistsRank=new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : artistRank.entrySet())
        {
            ArtistController artistC=new ArtistController();
            Artist artist=artistC.findById(entry.getKey());
            artistsRank.put(artist,entry.getValue());
        }*/
        List<Integer> top = new ArrayList<>(artistRank.values());
        Collections.sort(top, Integer::compareTo);
        System.out.println("Topul artistilor:");
        for (Integer i : top) {
            for (Map.Entry<Integer, Integer> entry : artistRank.entrySet()) {
                if (entry.getValue().equals(i)) {
                    ArtistController artistC = new ArtistController();
                    System.out.println(artistC.findById(entry.getKey()));
                    System.out.println(entry.getValue());
                }
            }
        }
    }

    Chart findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from chart where name = '" + name + "'");
        if (rs.next()) {
            Chart chart = new Chart(rs.getInt(1), rs.getString(2));
            return chart;
        } else {
            return null;
        }
    }
}
