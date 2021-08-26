FROM openjdk:8-jdk-alpine

ADD target/employees.jar employees.jar
ADD dev.yml dev.yml
ENTRYPOINT ["java", "-jar", "employees.jar", "server", "../dev.yml"]
Expose 8080
