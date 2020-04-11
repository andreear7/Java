import com.github.javafaker.Faker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Lab8PA {
    public static void main(String[] args) {
        try {
            Database database = Database.getDatabase();
            ArtistController artistC = new ArtistController();
            AlbumController albumC = new AlbumController();

            artistC.create("Ceva artist", "ceva tara");
            artistC.create("Alt artist", "din ceva tara");
            artistC.create("Si inca unul", "din aceeasi tara");
            Artist artist = artistC.findByName("Ceva artist");
            System.out.println(artist);
            albumC.create("Album1", artist.getId(), 1999);
            albumC.create("Album2", artist.getId(), 2000);
            albumC.create("Album3", 2, 2001);
            List<Album> albums = albumC.findByArtist(artist.getId());
            for (Album album : albums) {
                System.out.println(album);
            }

            //List<Album> albums=new ArrayList<>();
            //Optional

            int i;
            Faker faker = new Faker();
            for (i = 0; i < 10; i++) {
                artistC.create(faker.name().fullName(), faker.address().country());
            }
            for (i = 0; i < 20; i++) {
                Random rand = new Random();
                int number = rand.nextInt(9) + 1;
                String name = faker.name().firstName();
                albumC.create(name, number, 2020);
                Album albumm = albumC.findByName(name);
                albums.add(albumm);
            }
            ChartController chartC = new ChartController();
            chartC.createChart("Chart");
            Chart chart = chartC.findByName("Chart");
            int order = 1;
            for (Album album : albums) {
                chartC.addMusic(chart, album, order);
                order++;
            }
            // Map<Artist,Integer> artistRank;
            chartC.viewRanking();
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
