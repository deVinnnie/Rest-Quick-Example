package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @Pattern(regexp = "[0-9][0-9][0-9][0-9]")
    private String year;

    @ManyToOne
    @XmlTransient
    private Artist artist;

    public Album() {
    }

    public Album(String title) {
        this.title = title;
    }

    public Album(String title, String year, Artist artist) {
        this.title = title;
        this.year = year;
        this.artist = artist;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
