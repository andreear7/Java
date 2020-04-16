package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "artists", schema = "student")
@NamedQuery(name = "findAllArtistsWithName", query = "SELECT a FROM Artist a WHERE a.name LIKE :name")

public class Artist implements Serializable {

    private Integer id;
    private String name;
    private String country;

    /*  public Artist() {
      }

      public Artist(Integer id, String name, String country) {
          this.id = id;
          this.name = name;
          this.country = country;
      }
  */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_a")
    @SequenceGenerator(name = "seq_a", sequenceName = "seq_a", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
