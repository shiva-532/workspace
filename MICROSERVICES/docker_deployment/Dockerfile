FROM openjdk:16-alpine3.13

RUN apk --no-cache add curl

EXPOSE 9000

COPY first_example-0.0.1-SNAPSHOT.jar .

CMD java -jar first_example-0.0.1-SNAPSHOT.jar

