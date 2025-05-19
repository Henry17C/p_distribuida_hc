package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Book;
import com.programacion.distribuida.dtos.AuthorDto;
import com.programacion.distribuida.dtos.BookDto;
import com.programacion.distribuida.repo.BooksRepository;
import io.vertx.ext.web.client.impl.OAuth2AwareInterceptor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class BookRest {

    @Inject
    BooksRepository booksRepository;


    @GET
    @Path("/{isbn}")
    public Response findByIsbn(@PathParam("isbn") String isbn) {
    /*
    return booksRepository.findByIdOptional(isbn)
            .map(Response::ok)
            .orElse(Response.status(Response.Status.NOT_FOUND))
            .build();
    */
        BookDto ret = new BookDto();
        //1.buscar el Libro
        var obj= booksRepository.findByID(isbn);
        if (obj.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        var book = obj.get();
        ret.setIsbn(isbn);
        ret.setTitle(book.getTitle());
        ret.setPrice(book.getPrice());
        //2.buscar el inventario
        var inventary = book.getInventory();
        if(inventary != null) {
            //ret.setInventaySold(inventary.getSold());
           // ret.setInventaySupplied(inventary.getSupplied());
        }
        //3.buscar los autores
        var client = ClientBuilder.newClient();
        AuthorDto[] authors=client.target("http://localhost:8080" )
                .path("/authors/find/{isbn}")
                .resolveTemplate("isbn", isbn)
                .request(MediaType.APPLICATION_JSON)
                .get(AuthorDto[].class);
        ret.setAuthors(Stream.of(authors)
                .map(AuthorDto::getName)
                .toList()
        );
        return Response.ok(ret)
                .build();
    }

    @GET
    public List<Book> findAll() {
        return booksRepository.listAll();
    }
}