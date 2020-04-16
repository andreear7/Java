import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class AlbumManager {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = PersistenceUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AlbumRepository albumR = new AlbumRepository();
        ArtistRepository artistR = new ArtistRepository();
        entityManager.getTransaction().begin();
        Artist artist = new Artist();
        artist.setName("ARTIST");
        artist.setCountry("TARA");
        artistR.create(artist);
        Album album = new Album();
        album.setArtistId(1);
        album.setName("ALBUM");
        album.setReleaseYear(1999);
        albumR.create(album);
        Album altAlbum =new Album();
        altAlbum.setArtistId(1);
        altAlbum.setReleaseYear(2010);
        altAlbum.setName("ALT ALBUM");
        albumR.create(altAlbum);
        Album album1 = albumR.findById(1);
        System.out.println(album1);
        List<Album> albums;
        albums = albumR.findByArtistId(1);
        System.out.println("Find by artistId");
        for (Album album2 : albums) {
            System.out.println(album2);
        }
        List<Album> nameAlbums;
        nameAlbums = albumR.findByName("ALBUM");
        System.out.println("Find by name");
        for (Album album3 : nameAlbums) {
            System.out.println(album3);
        }
        Artist artist1 = artistR.findById(1);
        System.out.println(artist1);
        entityManager.close();

    }
}
