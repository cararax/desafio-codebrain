# syntax=docker/dockerfile:1

FROM openjdk:11

WORKDIR /app

COPY salesScoreSystem/.mvn/ .mvn
COPY salesScoreSystem/mvnw salesScoreSystem/pom.xml ./
RUN ./mvnw dependency:go-offline

COPY salesScoreSystem/src ./src

CMD ["./mvnw", "spring-boot:run"]
#
## syntax=docker/dockerfile:1
#
#FROM openjdk:11 as base
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]
#
#FROM base as test
#RUN ["./mvnw", "test"]
#
#FROM base as development
#CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]
#
#
#FROM base as build
#RUN ./mvnw package
#
#
#FROM openjdk:11-jre-slim as production
#
#
#COPY --from=build /app/target/springAppDemo-petclinic-*.jar /springAppDemo.jar
#
#CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/springAppDemo.jar"]
#
