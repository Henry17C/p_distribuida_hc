job "app-books" {
  datacenters = ["dc1"]
  type        = "service"

  group "app-books" {
    count = 2

    network {
      port "http" {
      }
    }

    task "app-books" {
      driver = "java"

      config {
        jar_path = "C:/Distribuida/asdfgh/p_distribuida_hc/traefik/app-books/quarkus-run.jar"

      }

      env {
        QUARKUS_HTTP_PORT = "${NOMAD_PORT_http}"
      }

      #Recursos de la máquina:
      resources {
        cpu    = 2000  # 2000 MHz
        memory = 1024  # 1 GB
      }

      service {
        provider = "nomad"
        name     = "app-books-http"
        port     = "http"
        tags     = ["quarkus-app"]
      }
    }
  }
}