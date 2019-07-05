FROM openjdk:8
WORKDIR /usr/src
ENV MONGO_DATABASE=ReminderDB
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
EXPOSE 8084
ADD ./target/ReminderService.jar /usr/src/ReminderService.jar
ENTRYPOINT ["java","-jar","/usr/src/ReminderService.jar"]