package com.youdi.ch01;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.ProducerConfig;
import com.youdi.ch02.ProducerInterCeptorPrefix;


import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerFastStar {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", brokerList);

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterCeptorPrefix.class.getName());

        // 配置生产者客户端参数并创建实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 构建消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hell0,kafka");

        try {
//            producer.send(record);


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
