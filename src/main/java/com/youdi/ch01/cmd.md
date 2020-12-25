## 创建
    bin/kafka-topics.sh --zookeeper localhost:2181/kafka --create --topic topic-demo --replication-factor 1 --partitions 4

## 查看
    bin/kafka-topics.sh --zookeeper localhost:2181/kafka --list

## 生产者
    bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic-demo

## 消费者
    ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092  --topic topic-demo --from-beginning