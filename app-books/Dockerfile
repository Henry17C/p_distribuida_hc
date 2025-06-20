

# docker build -t app-books .
# docker build -t nombre/app-books .

FROM eclipse-temurin:21.0.7_6-jre-noble

ENV LANGUAGE='en_US:en'

RUN mkdir /app
WORKDIR /app







COPY build/quarkus-app/lib ./lib
COPY build/quarkus-app/quarkus ./quarkus
COPY build/quarkus-app/app ./app
COPY build/quarkus-app/quarkus-run.jar app.jar

CMD ["java", "-jar", "app.jar"]

