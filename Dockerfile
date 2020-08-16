FROM openjdk:8
ADD target/challenge-everis-1.0.0-SNAPSHOT.jar challenge-everis-1.0.0.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","challenge-everis.jar"]