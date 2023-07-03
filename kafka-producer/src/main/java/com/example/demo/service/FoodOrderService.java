package com.example.demo.service;

import com.example.demo.model.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FoodOrderService {
    private final Producer producer;
    @Autowired
    public FoodOrderService(Producer producer) {
        this.producer = producer;
    }
    public String createFoodOrder(FoodOrder foodOrder) throws JsonProcessingException {
        return producer.sendMessage(foodOrder);
    }
}
