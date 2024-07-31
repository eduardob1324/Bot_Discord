FROM openjdk:17-jdk
RUN mkdir -p /home/app
COPY target/bot_spring-0.0.1.jar /home/app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/bot_spring-0.0.1.jar"]