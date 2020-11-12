# kafkademo
demo using kafka

Run Zoekeeper : zkserver

Run Kafka : 

kafka-server-start.bat C:\software\kafka_2.12-2.3.1\config\server.properties

Create Topic on Kafka :

kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1  --topic novice-players

Payload used on postman with endpoint : localhost:9090/players

[
    {
      "name": "Sub zero",
      "type": "expert"
    },
    {
      "name": "Scorpion",
      "type": "novice"
    },
    {
      "name": "Reptile",
      "type": "meh"
    }
  ]
  
  Command used to run the project : 
  
  mvn clean spring-boot:run
  
  Note : 
  used Conduktor to consume the topic and test that the even were arriving.


