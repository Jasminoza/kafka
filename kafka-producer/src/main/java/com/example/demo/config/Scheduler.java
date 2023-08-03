package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Scheduler {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private Integer count = 0;

    public Scheduler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 60_000)
    public void sendMessage() {
        count++;

        String text = "{\"item\": \"food\", \"amount\":" + count + "}";

        kafkaTemplate.send("t.scheduled", text);

        log.info("Message has been send: {}", text);
    }
}
