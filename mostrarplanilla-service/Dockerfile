FROM openjdk:17
ARG JAR_FILE=target/mostrarplanilla-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} mostrarplanilla-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mostrarplanilla-service-0.0.1-SNAPSHOT.jar"]