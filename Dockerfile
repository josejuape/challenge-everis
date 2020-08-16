FROM openjdk:8
ADD target/challenge-everis.jar challenge-everis.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","challenge-everis.jar"]