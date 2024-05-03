FROM adoptopenjdk/openjdk11:alpine

WORKDIR /app

COPY build/libs/groupmembers-service-1.0.0.jar /app/groupmembers-service-1.0.0.jar

EXPOSE 8080

CMD ["java", "-jar", "groupmembers-service-1.0.0.jar"]
