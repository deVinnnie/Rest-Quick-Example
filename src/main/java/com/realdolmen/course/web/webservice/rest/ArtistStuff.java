package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Album;
import com.realdolmen.course.domain.Artist;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VCLBB37 on 22/09/2016.
 */
@Stateless
public class ArtistStuff {

    private List<Artist> artistList;

    @PostConstruct
    public void setUp(){
        this.artistList = Arrays.asList(
                new Artist(1, "ABBA"),
                new Artist(2, "Fleetwood Mac"),
                new Artist(3, "Hooverphonic")
        );

        artistList.get(0).setAlbums(
            Arrays.asList(
                    new Album(1, "Waterloo"),
                    new Album(2, "The Visitors"),
                    new Album(3, "Gold"),
                    new Album(5, "Ring Ring")
            )
        );

        for(Album album : artistList.get(0).getAlbums()){
            album.setArtist(artistList.get(0));
        }
    }

    public List<Artist> getArtists(){
        return this.artistList;
    }

    public List<Album> getAlbums(){
        ArrayList<Album> albums = new ArrayList<>();
        for(Artist artist : this.artistList){
            albums.addAll(artist.getAlbums());
        }
        return albums;
    }

    public Album albumById(Integer id){
        for(Album album : this.getAlbums()){
            if(album.getId().equals(id)){
                return album;
            }
        }
        return null;
    }
}
