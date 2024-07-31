FROM openjdk:17-jdk
RUN mkdir -p /home/app
COPY target/bot-spring-0.0.1-SNAPSHOT.jar /home/app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/bot-spring-0.0.1-SNAPSHOT.jar"]