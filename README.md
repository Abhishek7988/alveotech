Follow the following steps to build and run the project.

The time interval can be specified in application.yml property monitoring.interval. (the same can be updated from the UI).

The log path needs to be update to the machine's local in application.yml property file.path

To create a jar from the project run the following command
mvn install

To run the project
java -jar log-reader-0.0.1-SNAPSHOT.jar

To run the UI page
http://localhost:8080/index.html

To Check the bacjkend endpoints using swagger
http://localhost:8080/swagger-ui.html

The log file path can 