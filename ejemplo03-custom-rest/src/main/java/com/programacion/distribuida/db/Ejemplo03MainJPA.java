package com.programacion.distribuida.db;

import com.programacion.distribuida.db.db.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Ejemplo03MainJPA {
    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("posts-unit");
        EntityManager em = emf.createEntityManager();

        var post1 = em.find(Post.class, 1);
        System.out.println(post1);
    }
}
