FROM openjdk:17-alpine
EXPOSE 8080
RUN pwd
ADD / /etc/demo-service
RUN cp /etc/demo-service/target/DemoJPA-0.0.1-SNAPSHOT.jar DemoJPA-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/upload-service-1.0.jar"]
