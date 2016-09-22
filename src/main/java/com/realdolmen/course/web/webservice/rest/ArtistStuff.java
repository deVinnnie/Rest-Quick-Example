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
                                em.merge(new Album("Waterloo")),
                                em.merge(new Album("The Visitors")),
                                em.merge(new Album("Gold")),
                                em.merge(new Album("Ring Ring"))
                        )
                )
        );

        Artist fleetwood = new Artist("Fleetwood Mac");
        em.persist(fleetwood);

        fleetwood.setAlbums(
                Arrays.asList(
                    em.merge(new Album("Rumours"))
                )
        );

        Artist hoover = new Artist("Hooverphonic");
        em.persist(hoover);

        hoover.setAlbums(
            Arrays.asList(
                em.merge(new Album("The Magnificent Tree")),
                em.merge(new Album("No more sweet music")),
                em.merge(new Album("Blue Wonder Power Milk"))
            )
        );
    }

    public List<Artist> getArtists() {
        return em.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
    }

    public List<Album> getAlbums() {
        return em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
        /*ArrayList<Album> albums = new ArrayList<>();
        for (Artist artist : this.artistList) {
            albums.addAll(artist.getAlbums());
        }
        return albums;*/
    }

    public Album albumById(Integer id) {
        return em.find(Album.class, id);
        /*for (Album album : this.getAlbums()) {
            if (album.getId().equals(id)) {
                return album;
            }
        }
        return null;*/
    }

    public Artist addArtist(ArtistCreateDTO artistDTO) {
        Artist artist = new Artist(artistDTO.getName());
        //this.artistList.add(artist);
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
}
