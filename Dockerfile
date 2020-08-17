FROM openjdk:8

ADD target/challenge-everis-h2.jar challenge-everis-h2.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","challenge-everis-h2.jar"]