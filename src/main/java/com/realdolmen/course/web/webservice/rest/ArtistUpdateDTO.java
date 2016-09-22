package com.realdolmen.course.web.webservice.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArtistUpdateDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
