package com.programacion.distribuida.db.rs;

import com.programacion.distribuida.db.db.Post;
import com.programacion.distribuida.db.repo.PostRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/posts")
public class PostRest {

    @Inject
    PostRepository postRepository;

    @GET
    public List<Post> findAll() {
        return postRepository.findAll();
    }
    @GET
    @Path("/{postId}")
    public Post findById(@PathParam("postId") Integer id) {
        return postRepository
                .findId(id)
                .orElse(null);
    }
}
