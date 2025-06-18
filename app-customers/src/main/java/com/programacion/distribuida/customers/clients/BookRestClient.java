package com.programacion.distribuida.customers.clients;

import com.programacion.distribuida.customers.dto.BookDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.math.BigDecimal;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "stork://books-api")
public interface BookRestClient {
    @GET
    @Path("/{isbn}")
    @Retry(maxRetries = 3, delay = 1000) // Retry up to 3 times with a 1 second delay
    @Fallback(fallbackMethod = "findByBookFallback")
    BookDto findByBook(@PathParam("isbn") String isbn);

    default BookDto findByBookFallback( String isbn) {
        var bookDto = new BookDto();
        bookDto.setIsbn(null);
        bookDto.setAuthors(null);
        bookDto.setTitle("No Book Title");
        bookDto.setPrice(new BigDecimal(0));
        bookDto.setInventorySold(0);

        return bookDto;
    }


}
