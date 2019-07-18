FROM openjdk:8-alpine

# Required for starting application up.
RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY build/libs/springBootProject-1.0.jar $PROJECT_HOME/springBootProject.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongo:27017/user","-Djava.security.egd=file:/dev/./urandom","-jar","./springBootProject.jar"]