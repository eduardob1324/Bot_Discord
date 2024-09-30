FROM openjdk:17-jdk
#RUN mkdir -p /home/app
COPY target/bot-spring-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
