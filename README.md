# kafka

Install kafka/zookeeper and start servers
$ brew install kafka
$ brew install zookeeper
$ zkServer start
$ kafka-server-start /usr/local/etc/kafka/server.properties

Create a topic
$ kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

Send a message
$ kafka-console-producer --broker-list localhost:9092 --topic test
>HELLO Kafka

Consume message
$ kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning
HELLO Kafka

To list topics
kafka-topics --list  --bootstrap-server localhost:9092

