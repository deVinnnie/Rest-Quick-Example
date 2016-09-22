package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Album;
import com.realdolmen.course.domain.Artist;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

/**
 * This is an endpoint for a JAX-RS RESTful web service.
 */
@Path("/artists")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class ArtistRestServiceEndpoint {

    @EJB
    public ArtistStuff artistsStuff;


    @GET
    public ArtistList findAll() {
        return new ArtistList(artistsStuff.getArtists());
    }

    @GET
    @Path("{id : \\d+}")
    public Artist find(@PathParam("id") Integer id){
        return artistsStuff.getArtists().get(--id);
    }
}
