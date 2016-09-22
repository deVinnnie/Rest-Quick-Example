package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * Created by VCLBB37 on 22/09/2016.
 */
@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @ManyToOne
    @XmlTransient
    private Artist artist;

    public Album() {
    }

    public Album(String title) {
        this.title = title;
    }

    public Album(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
