package com.youdi.ch01;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerFastStar {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", brokerList);

        // 配置生产者客户端参数并创建实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 构建消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hell0,kafka");

        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
        System.out.println("ok");
    }

}
