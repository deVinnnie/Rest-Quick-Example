package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Album;
import com.realdolmen.course.domain.Artist;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class ArtistStuff {

    private ArrayList<Artist> artistList;

    private int artistId = 0;

    @PostConstruct
    public void setUp() {
        this.artistList = new ArrayList<>(
                Arrays.asList(
                        new Artist(1, "ABBA"),
                        new Artist(2, "Fleetwood Mac"),
                        new Artist(3, "Hooverphonic")
                )
        );

        artistList.get(0).setAlbums(
                new ArrayList<>(
                        Arrays.asList(
                                new Album(1, "Waterloo"),
                                new Album(2, "The Visitors"),
                                new Album(3, "Gold"),
                                new Album(5, "Ring Ring")
                        )
                )
        );

        for (Album album : artistList.get(0).getAlbums()) {
            album.setArtist(artistList.get(0));
        }

        artistList.get(2).setAlbums(
                Arrays.asList(
                        new Album(22, "The Magnificent Tree"),
                        new Album(23, "No more sweet music"),
                        new Album(24, "Blue Wonder Power Milk")
                )
        );

        for (Album album : artistList.get(2).getAlbums()) {
            album.setArtist(artistList.get(0));
        }
    }

    public List<Artist> getArtists() {
        return this.artistList;
    }

    public List<Album> getAlbums() {
        ArrayList<Album> albums = new ArrayList<>();
        for (Artist artist : this.artistList) {
            albums.addAll(artist.getAlbums());
        }
        return albums;
    }

    public Album albumById(Integer id) {
        for (Album album : this.getAlbums()) {
            if (album.getId().equals(id)) {
                return album;
            }
        }
        return null;
    }

    public Artist addArtist(ArtistCreateDTO artistDTO) {
        Artist artist = new Artist(++artistId, artistDTO.getName());
        this.artistList.add(artist);
        return artist;
    }

    public Artist updateArtist(Integer id, ArtistUpdateDTO artistDTO) {
        Artist artist = this.artistList.get(--id);
        artist.setName(artistDTO.getName());
        return artist;
    }
}
