package com.programacion.distribuida.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {

    @Inject
    DataSource dataSource;

    @Override
    public HealthCheckResponse call() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(2)) {
                return HealthCheckResponse.up("Conexión a base de datos verificada");
            } else {
                return HealthCheckResponse.down("Conexión no válida");
            }
        } catch (SQLException e) {
            return HealthCheckResponse.down("No se puede conectar a la BD: " + e.getMessage());
        }
    }
}