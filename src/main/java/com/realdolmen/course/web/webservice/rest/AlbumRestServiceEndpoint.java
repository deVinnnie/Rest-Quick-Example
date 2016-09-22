package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Album;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/albums")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class AlbumRestServiceEndpoint {

    @EJB
    private ArtistStuff artistStuff;

    @GET
    public AlbumList findAll(@QueryParam("year") String year){
        if(year == null || year.isEmpty()){
            return new AlbumList(artistStuff.getAlbums());
        }
        return new AlbumList(artistStuff.findAlbumByYear(year));
    }

    @GET
    @Path("{id : \\d+}")
    public Album find(@PathParam("id") Integer id){
        return artistStuff.albumById(id);
    }
}
