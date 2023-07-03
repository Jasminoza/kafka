package com.example.demo;

import com.example.demo.dto.FoodOrderDto;
import com.example.demo.model.FoodOrder;
import com.example.demo.service.FoodOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class Consumer {
    private static final String orderTopic = "${topic.name}";

    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final FoodOrderService foodOrderService;

    @Autowired
    public Consumer(ObjectMapper objectMapper, FoodOrderService foodOrderService, ModelMapper modelMapper) {
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
        this.foodOrderService = foodOrderService;
    }

    @KafkaListener(topics = orderTopic)
    public void consumeMessage(String message) throws JsonProcessingException {
        log.info(LocalDateTime.now() + ": message consumed {}", message);

        FoodOrderDto foodOrderDto = objectMapper.readValue(message, FoodOrderDto.class);
        FoodOrder foodOrder = modelMapper.map(foodOrderDto, FoodOrder.class);

        foodOrderService.persistFoodOrder(foodOrder);
    }
}
