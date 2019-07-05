FROM openjdk:8
WORKDIR /usr/src
ENV MYSQL_DATABASE=keepnote
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/keepnote
EXPOSE 8081
ADD ./target/UserAuthService.jar /usr/src/UserAuthService.jar
ENTRYPOINT ["java","-jar","/usr/src/UserAuthService.jar"]
