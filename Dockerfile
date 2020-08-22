# build stage
# FROM adoptopenjdk:11-jdk-hotspot as build
FROM gradle:6.6.0-jdk11 as build
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./

# Only download dependencies
# Eat the expected build failure since no source code has been copied yet
RUN gradle assemble --no-daemon > /dev/null 2>&1 || true

# copy app files
COPY . .

# build app without tests
RUN gradle assemble --no-daemon


# target stage
FROM adoptopenjdk:11-jre-hotspot
COPY --from=build /app/build/libs/demo-*.jar /app/demo.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]
