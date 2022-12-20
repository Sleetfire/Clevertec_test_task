FROM eclipse-temurin:17-alpine
MAINTAINER Sleetfire

ADD ./build/libs/check_runner-0.1.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/check_runner-0.1.jar"]

EXPOSE 8080 8089