FROM openjdk:8
ADD target/challenge-everis.jar challenge-everis.jar
EXPOSE 8081

COPY cartificates/certificate_mongo.cer /etc/pki/ca-trust/source/anchors/certificate-mongo.crt

RUN /bin/update-ca-trust

ENTRYPOINT ["java","-jar","challenge-everis.jar"]