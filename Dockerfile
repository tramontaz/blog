FROM openjdk:8-jdk-alpine
WORKDIR /
ADD /target/blog-0.0.1-SNAPSHOT.jar app.jar
COPY /src/main/resources/application.yml application.yml
EXPOSE 80
CMD java -jar app.jar