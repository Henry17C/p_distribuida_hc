package com.programacion.distribuida.db.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StringServiceImpl implements StringService {

    @Inject
     LogService log;

//     @PostConstruct
//     public void init() {
//         log= CDI.current().
//                 select(LogService.class).
//                 get();
//     }
    @Override
    public String convert(String txt) {
        log.print("Convertiendo: " + txt);
        return txt.toUpperCase();
    }
}
