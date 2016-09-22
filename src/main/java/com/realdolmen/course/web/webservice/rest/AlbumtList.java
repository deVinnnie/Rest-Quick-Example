package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Album;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This is a wrapper so that JAXB can create a <code>&lt;people&gt;</code> tag out of a <code>java.util.List&lt;People&gt;</code>.
 */
@XmlRootElement(name = "albums")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlbumtList {

    @XmlElement(name = "album")
    private List<Album> albums;

    public AlbumtList() {
    }

    public AlbumtList(List<Album> albums) {
        this.albums = albums;
    }

    public List<Album> getPersons() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
