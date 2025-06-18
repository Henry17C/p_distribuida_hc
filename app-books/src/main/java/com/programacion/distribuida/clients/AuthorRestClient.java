package com.programacion.distribuida.clients;


import com.programacion.distribuida.dtos.AuthorDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
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
    @Retry(maxRetries = 3, delay = 1000) // Retry up to 3 times with a 1 second delay
    @Fallback(fallbackMethod = "findByBookFallback")
    public List<AuthorDto> findByBook(@PathParam("isbn") String isbn);

    /*default AuthorDto findByBookFallback(String isbn) {
    var dto= new AuthorDto();
    dto.setName("No Author Found");
    dto.setId("-1");

    return dto; // Return an empty list as a fallback
}*/

    default List<AuthorDto> findByBookFallback(String isbn) {
        AuthorDto dto = new AuthorDto();
        dto.setName("No Author Found");
        dto.setId("-1");
        return List.of(dto);
    }
}
