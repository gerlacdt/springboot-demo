FROM amazoncorretto:11
COPY ./build/libs/demo-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
