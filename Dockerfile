#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /target/DemoJPA-0.0.1-SNAPSHOT.jar DemoJPA-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/DemoJPA-0.0.1.jar"]
