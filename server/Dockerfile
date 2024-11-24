FROM maven:3.8.5-openjdk-17 as build

WORKDIR /build
COPY . .
RUN mvn clean package -Dskiptests


FROM openjdk:17
#FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build  ./build/target/*.jar ./ixtore_sever.jar

ENTRYPOINT java -jar ixtore_sever.jar
