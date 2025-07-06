# docker build -t app-authors .
# docker build -t henry170/app-authors .
# docker push henry170/app-authors


FROM eclipse-temurin:21.0.7_6-jre-alpine-3.21
ENV LANGUAGE='en_US:en'

# Se crea un directorio para la aplicacion
RUN mkdir /app
WORKDIR /app

# Copiar los archivos de la aplicacion al contenedor
# Se pone primero la capa de dependencias o lib para que se pueda reutilizar.
COPY build/quarkus-app/lib/ ./lib
COPY build/quarkus-app/quarkus ./quarkus
COPY build/quarkus-app/app/ ./app
COPY build/quarkus-app/quarkus-run.jar app.jar
#El nombre del jar lo cambiamos a app.jar

CMD ["java", "-jar", "app.jar"]

