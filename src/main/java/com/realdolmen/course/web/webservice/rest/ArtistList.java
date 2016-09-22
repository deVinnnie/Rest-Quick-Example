package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Artist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This is a wrapper so that JAXB can create a <code>&lt;people&gt;</code> tag out of a <code>java.util.List&lt;People&gt;</code>.
 */
@XmlRootElement(name = "artists")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArtistList {

    @XmlElement(name = "artist")
    private List<Artist> artists;

    public ArtistList() {
    }

    public ArtistList(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Artist> getPersons() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
