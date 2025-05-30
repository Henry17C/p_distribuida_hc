package com.programacion.distribuida.clients;


import com.programacion.distribuida.db.Book;
import com.programacion.distribuida.dtos.AuthorDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri = "http://localhost:8081")
//@RegisterRestClient(configKey = "authors.api")
@RegisterRestClient(baseUri = "stork://authors-api")
public interface AuthorRestClient {

    @GET
    @Path("/find/{isbn}")
    public List<AuthorDto> findByBook(@PathParam("isbn") String isbn);
}
