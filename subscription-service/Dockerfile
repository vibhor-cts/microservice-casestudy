FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} subscriptionservice.jar
ENTRYPOINT ["java","-jar","/subscriptionservice.jar"]