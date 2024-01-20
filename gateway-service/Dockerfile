FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY . /app
RUN apt-get update && apt-get install -y dos2unix && dos2unix gradlew
RUN find ./ -name "*.java" | xargs dos2unix
RUN ./gradlew build

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/gateway-service-0.0.1-SNAPSHOT.jar /app/gateway-service-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "gateway-service-0.0.1-SNAPSHOT.jar"]