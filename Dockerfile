FROM maven:3.6.3-jdk-11 AS builder

WORKDIR /app
ADD pom.xml /app
RUN mvn dependency:go-offline

FROM builder
COPY . /app
RUN mvn clean install -DskipTests -e

ENTRYPOINT ["java","-jar","/app/target/simpledatawarehouse-1.0-SNAPSHOT.jar"]