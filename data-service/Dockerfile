FROM openjdk:17
ARG JAR_FILE=target/data-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} data-service-0.0.1-SNAPSHOT.jar
COPY Data.txt Data.txt
ENTRYPOINT ["java","-jar","/data-service-0.0.1-SNAPSHOT.jar"]]