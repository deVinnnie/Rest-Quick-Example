package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Album;
import com.realdolmen.course.domain.Artist;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class ArtistStuff {

    private ArrayList<Artist> artistList = new ArrayList<>();


    @PersistenceContext(unitName = "InMemory")
    private EntityManager em;

    @PostConstruct
    public void setUp() {
        Artist abba = new Artist("ABBA");
        em.persist(abba);

        abba.setAlbums(
                new ArrayList<>(
                        Arrays.asList(
                                em.merge(new Album("Waterloo", "1974", abba)),
                                em.merge(new Album("The Visitors", "1981", abba)),
                                em.merge(new Album("Gold", "1992", abba)),
                                em.merge(new Album("Ring Ring", "1973", abba))
                        )
                )
        );

        Artist fleetwood = new Artist("Fleetwood Mac");
        em.persist(fleetwood);

        fleetwood.setAlbums(
                Arrays.asList(
                    em.merge(new Album("Rumours", "1977", fleetwood))
                )
        );

        Artist hoover = new Artist("Hooverphonic");
        em.persist(hoover);

        hoover.setAlbums(
            Arrays.asList(
                em.merge(new Album("The Magnificent Tree", "2000", hoover)),
                em.merge(new Album("No more sweet music", "2005", hoover)),
                em.merge(new Album("Blue Wonder Power Milk", "1998", hoover))
            )
        );
    }

    public List<Artist> getArtists() {
        return em.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
    }

    public List<Album> getAlbums() {
        return em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
    }

    public Album albumById(Integer id) {
        return em.find(Album.class, id);
    }

    public Artist addArtist(ArtistCreateDTO artistDTO) {
        Artist artist = new Artist(artistDTO.getName());
        em.persist(artist);
        return artist;
    }

    public Artist updateArtist(Integer id, ArtistUpdateDTO artistDTO) {
        Artist artist = em.find(Artist.class, id);
        artist.setName(artistDTO.getName());

        //Artist artist = this.artistList.get(--id);
        artist.setName(artistDTO.getName());
        return artist;
    }

    public void deleteArtist(Integer id) {
        em.remove(em.find(Artist.class, id));
        //this.artistList.remove((int) --id);
    }

    public List<Album> findAlbumByYear(String year){
        return em.createQuery("SELECT a FROM Album a WHERE a.year = :year", Album.class)
                .setParameter("year", year)
                .getResultList();
    }
}
