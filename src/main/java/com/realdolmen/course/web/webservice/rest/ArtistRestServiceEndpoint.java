package com.realdolmen.course.web.webservice.rest;

import com.realdolmen.course.domain.Artist;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

/**
 * This is an endpoint for a JAX-RS RESTful web service.
 */
@Path("/artists")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class ArtistRestServiceEndpoint {

    @EJB
    public ArtistStuff artistsStuff;

    @Context
    UriInfo uriInfo;


    @GET
    public ArtistList findAll() {
        return new ArtistList(artistsStuff.getArtists());
    }

    @GET
    @Path("{id : \\d+}")
    public Artist find(@PathParam("id") Integer id){
        return artistsStuff.getArtists().get(--id);
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response createArtist(ArtistCreateDTO artistDTO) {
        Artist artist = artistsStuff.addArtist(artistDTO);

        // Build URI which references the location of the new resource.
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI location = uriBuilder.path("artists")
                .path("{id}")
                .build(artist.getId());

        // Return 201 Response
        Response.ResponseBuilder builder = Response.ok();
        Response response = builder.status(Response.Status.CREATED)
                .location(location)
                .build();
        return response;
    }

}
