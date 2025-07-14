# docker build -t app-customers .
# docker build -t henry170/app-customers .
# docker push henry170/app-customers



FROM eclipse-temurin:21.0.7_6-jre-noble
ENV LANGUAGE='en_US:en'

# Se crea un directorio para la aplicacion
RUN mkdir /app
WORKDIR /app

RUN apt update
RUN apt install -y iputils-ping

# Copiar los archivos de la aplicacion al contenedor
# Se pone primero la capa de dependencias o lib para que se pueda reutilizar.
COPY build/quarkus-app/lib/ ./lib
COPY build/quarkus-app/quarkus ./quarkus
COPY build/quarkus-app/app/ ./app
COPY build/quarkus-app/quarkus-run.jar app.jar
#El nombre del jar lo cambiamos a app.jar

CMD ["java", "-jar", "app.jar"]

