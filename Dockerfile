FROM maven:3.8.6-openjdk-8

WORKDIR /app

COPY . .

#RUN mvn clean
#
#RUN mvn clean package assembly:single

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "./target/if3110-moi-soap-service-1.0-SNAPSHOT-jar-with-dependencies.jar"]