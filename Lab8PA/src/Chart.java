import java.util.HashMap;
import java.util.Map;

public class Chart {
    Integer id;
    String name;
    Map<Album, Integer> albums = new HashMap<>();

    public Chart(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Album, Integer> getAlbums() {
        return albums;
    }

    public void setAlbums(Map<Album, Integer> albums) {
        this.albums = albums;
    }
}
