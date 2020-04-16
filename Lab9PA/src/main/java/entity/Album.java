package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "albums", schema = "student")
@NamedQueries({
        @NamedQuery(name = "findAllAlbumsWithName", query = "SELECT a FROM Album a WHERE a.name LIKE :name"),
        @NamedQuery(name = "findAlbumsByArtistId", query = "SELECT ab FROM Album ab WHERE ab.artistId = :artistId")})

public class Album implements Serializable {

    private int id;
    private String name;
    private int artistId;
    private int releaseYear;

    /* public Album(int id, String nume, int artistId, int releaseYear) {
         this.id = id;
         this.name = nume;
         this.artistId = artistId;
         this.releaseYear = releaseYear;
     }
 */
    public Album() {

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ar")
    @SequenceGenerator(name = "seq_ar", sequenceName = "seq_ar", allocationSize = 1)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "artist_id")
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "release_year")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artistId=" + artistId +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
