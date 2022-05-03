FROM openjdk:11.0.12
ADD ./target/company-0.0.1-SNAPSHOT.jar company-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "company-0.0.1-SNAPSHOT.jar"]
EXPOSE 8085