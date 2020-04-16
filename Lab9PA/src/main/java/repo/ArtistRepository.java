package repo;

import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.*;
import java.util.List;

public class ArtistRepository {
    private EntityManagerFactory factory;

    public ArtistRepository() {
        this.factory = PersistenceUtil.getEntityManagerFactory();
    }

    public void create(Artist artist) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Artist> findByName(String name) {
        EntityManager entityManager = factory.createEntityManager();
        List<Artist> artists = entityManager.createNamedQuery("findAllArtistsWithName").setParameter("name", name).getResultList();
        //List<Artist> artists = query.getResultList();
        entityManager.close();
        return artists;
    }

    public Artist findById(Integer artistId) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("select a from Artist a where a.id=:artistId");
        Artist artist = (Artist) query.setParameter("artistId", artistId).getSingleResult();
        entityManager.close();
        return artist;
    }
}
