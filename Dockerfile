FROM java:8

EXPOSE 8080

ADD target/list-app.jar list-app.jar

ENTRYPOINT ["java","-jar","list-app.jar"]

git status