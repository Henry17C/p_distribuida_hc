package com.programacion.distribuida.db;
import com.programacion.distribuida.db.service.StringService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;


public class PrincipalCdi {
    //@Inject
    //StringService service;

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            // Aquí va tu código de trabajo con el contenedor.


            StringService service = container.
                    select(StringService.class).
                    get();

            String ret = service.convert("Hola mundo  xd");
            System.out.println(ret);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
