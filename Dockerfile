FROM openjdk:21-jdk-slim-buster
WORKDIR /app
COPY target/emailNotification-0.0.1-SNAPSHOT.jar ./emailNotification.jar
EXPOSE 8099
ENTRYPOINT ["java", "-jar", "emailNotification.jar", "--spring.kafka.bootstrap-servers=broker:9092", "--spring.kafka.consumer.bootstrap-servers=broker:9092", "--spring.profiles.active=cloudConfig", "--eureka.client.service-url.defaultZone=http://eureka-server:8761/eureka", "--spring.mail.host=mail"]