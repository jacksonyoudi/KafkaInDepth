package com.youdi.ch02;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class ProducerInterCeptorPrefix implements ProducerInterceptor<String, String> {
    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String value1 = "prefix1-" + record.value();
        return new ProducerRecord<String, String>(record.topic(),
                record.partition(), record.timestamp(),
                record.key(), value1, record.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            sendSuccess++;
        } else {
            sendFailure++;
        }
    }

    @Override
    public void close() {
        System.out.println(
                "sucees:" + sendSuccess + "fail:" + sendFailure
        );
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
