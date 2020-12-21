package com.youdi.ch02;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerFastStar {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CompanySerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);


        // 配置生产者客户端参数并创建实例
        KafkaProducer<String, Company> producer = new KafkaProducer<String, Company>(properties);

        // 构建消息
        Company company = Company.builder().name("hello").address("sz").build();

        ProducerRecord<String, Company> record = new ProducerRecord<String, Company>(topic, company);


        try {
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata recordMetadata = future.get();

            System.out.println(recordMetadata);

        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
        System.out.println("ok");
    }

}
