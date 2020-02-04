FROM openjdk:11-jdk as build
VOLUME /tmp
COPY . .
RUN ./gradlew clean build

FROM openjdk:11-jdk
WORKDIR /app
COPY --from=build build/libs/*.jar app.jar
ARG JAR_FILE
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080

#docker build -t hello-rest-controller .
#docker run -d -p8080:8080 --rm hello-rest-controller 