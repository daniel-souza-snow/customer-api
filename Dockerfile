FROM openjdk:11
VOLUME /tmp
ADD ./target/customer-api-0.0.1-SNAPSHOT.jar customer-api.jar
ENTRYPOINT ["java","-jar","/customer-api.jar"]