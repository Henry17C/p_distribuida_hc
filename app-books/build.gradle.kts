plugins {
    id("java")
    id("io.freefair.lombok") version "8.13.1"
    id("io.quarkus") version "3.22.2"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}
val quarkusVersion = "3.22.2"
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {



    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusVersion"))

    //CDI
    implementation("io.quarkus:quarkus-arc")

    //REST
    implementation("io.quarkus:quarkus-rest")
    implementation("io.quarkus:quarkus-rest-jsonb")

    implementation("io.quarkus:quarkus-rest-client")
    implementation("io.quarkus:quarkus-rest-client-jsonb")

    //JPA
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("org.modelmapper:modelmapper:3.2.2")

    //Control de versiones Flyway
    /*implementation("io.quarkus:quarkus-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")*/

    //Service Discovery

    implementation("io.quarkus:quarkus-smallrye-stork")
    implementation("io.smallrye.stork:stork-service-discovery-static-list")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")

}

