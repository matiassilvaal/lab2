FROM openjdk:17
ARG JAR_FILE=target/eureka-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eureka-service-0.0.1-SNAPSHOT.jar
COPY Data.txt Data.txt
ENTRYPOINT ["java","-jar","/eureka-service-0.0.1-SNAPSHOT.jar"]]