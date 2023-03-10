FROM openjdk:17-alpine
EXPOSE 8080
RUN pwd
ADD / /etc/upload-service
RUN cp /etc/upload-service/target/upload-service-1.0.jar upload-service-1.0.jar
ENTRYPOINT ["java","-jar", "/upload-service-1.0.jar"]
