package repo;
import entity.Album;
import entity.Artist;
import util.PersistenceUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AlbumRepository {
    private EntityManagerFactory factory;

    public AlbumRepository() {
        this.factory = PersistenceUtil.getEntityManagerFactory();
    }

    public void create(Album album) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Album> findByName(String name) {
        EntityManager entityManager = factory.createEntityManager();
        List<Album> albums = entityManager.createNamedQuery("findAllAlbumsWithName").setParameter("name","%"+ name + "%").getResultList();
        // List<Album> albums = query.setParameter("name", name).getResultList();
        entityManager.close();
        return albums;
    }

    public Album findById(Integer id) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select a from Album a where a.id=:id");
        Album album = (Album) query.setParameter("id", id).getSingleResult();
        entityManager.close();
        return album;
    }

    public List<Album> findByArtistId(Integer artistId) {
        EntityManager entityManager = factory.createEntityManager();
        List<Album> albums = entityManager.createNamedQuery("findAlbumsByArtistId").setParameter("artistId", artistId).getResultList();
        // List<Album> albums = query.setParameter("name", name).getResultList();
        entityManager.close();
        return albums;
    }
}
