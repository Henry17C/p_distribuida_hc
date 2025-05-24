package com.programacion.distribuida.rest;

import com.programacion.distribuida.clients.AuthorRestClient;
import com.programacion.distribuida.db.Book;
import com.programacion.distribuida.dtos.AuthorDto;
import com.programacion.distribuida.dtos.BookDto;
import com.programacion.distribuida.repo.BooksRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.modelmapper.ModelMapper;

import java.util.List;


@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class BookRest {



    @Inject
    BooksRepository booksRepository;

    @Inject
    ModelMapper mapper;


    @Inject
    @ConfigProperty(name = "authors.url")
    String authorsUrl;

    @Inject
    @RestClient
    private AuthorRestClient client;

    /*
    @PostConstruct
    void init() {
        client = RestClientBuilder.newBuilder()
                .baseUrl(authorsUrl)
                .build(AuthorRestClient.class);
    }
*/

    @GET
    @Path("/{isbn}")
    public Response findByIsbn(@PathParam("isbn") String isbn) {

        BookDto ret  = new BookDto();

        // 1. Buscar el libro
        var obj = booksRepository.findByIdOptional(isbn);
        if (obj.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mapper.map(obj.get(),ret);

       // var book = obj.get();
        var authors = client.findByBook(isbn)
                .stream()
                .map(AuthorDto::getName)
                .toList();

        ret.setAuthors(authors);


        return Response.ok(ret).build();


    }



    @GET
    public List<BookDto> findAll() {
        AuthorRestClient client = RestClientBuilder.newBuilder()
                .baseUri(authorsUrl)
                .build(AuthorRestClient.class);

        return booksRepository.streamAll()
                .map(book -> {
                    var dto = new BookDto();
                    mapper.map(book, dto);
                    return dto;
                })
                .map(book -> {
                    var authors = client.findByBook(book.getIsbn())
                            .stream()
                            .map(AuthorDto::getName)
                            .toList();
                    book.setAuthors(authors);
                    return book;
                })
                .toList();
    }

    @POST
    public void insert(Book book) {
        booksRepository.persist(book);
    }

}