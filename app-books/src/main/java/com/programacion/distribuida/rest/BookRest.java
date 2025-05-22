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
    public Response findById(@PathParam("isbn") String isbn) {


        BookDto bookDto = new BookDto();

        // 1. Buscar el libro
        var obj = booksRepository.findByIdOptional(isbn);
        if (obj.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var book = obj.get();

        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());

        // 2. Buscar el inventario

        var inventory = book.getInventory();
        if (inventory != null) {
            bookDto.setInventorySold(inventory.getSold());
            bookDto.setInventorySupplied(inventory.getSupplied());
        }

        // 3  Buscar los autores
        var client = ClientBuilder.newClient();
        AuthorDto[] authors = client.target("http://localhost:8080")
                .path("authors/find/{isbn}")
                .resolveTemplate("isbn", isbn)
                .request(MediaType.APPLICATION_JSON)
                .get(AuthorDto[].class);
        bookDto.setAuthors(
                Stream.of(authors)
                        .map(AuthorDto::getName)
                        .toList()
        );


        return Response.ok(bookDto).build();


    }

    @GET
    public List<Book> findAll() {
        return booksRepository.listAll();
    }

    @POST
    public void insert(Book book) {
        booksRepository.persist(book);
    }

}