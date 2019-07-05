FROM openjdk:8
WORKDIR /usr/src
ENV MONGO_DATABASE=keepnote
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
EXPOSE 8089
ADD ./target/NoteService.jar /usr/src/NoteService.jar
ENTRYPOINT ["java","-jar","/usr/src/NoteService.jar"]