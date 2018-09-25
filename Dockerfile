FROM java:8

COPY bin/pos.jar /pos.jar

EXPOSE 8080

CMD java -jar /pos.jar
