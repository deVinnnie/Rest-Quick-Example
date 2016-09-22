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
 * Created by VCLBB37 on 22/09/2016.
 */
@Path("/albums")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class AlbumRestServiceEndpoint {

    @EJB
    private ArtistStuff artistStuff;

    @GET
    public AlbumtList findAll() {
        return new AlbumtList(artistStuff.getAlbums());
    }

    @GET
    @Path("{id : \\d+}")
    public Album find(@PathParam("id") Integer id){
        return new Album(id, "Waterloo");
    }
}
