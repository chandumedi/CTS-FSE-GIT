FROM openjdk:8
WORKDIR /usr/src
ENV MONGO_DATABASE=UserDB
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
EXPOSE 8084
ADD ./target/UserService.jar /usr/src/UserService.jar
ENTRYPOINT ["java","-jar","/usr/src/UserService.jar"]