package com.example.demo.service;

import com.example.demo.model.FoodOrder;
import com.example.demo.repository.FoodOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FoodOrderService {
    private final FoodOrderRepository foodOrderRepository;

    @Autowired
    public FoodOrderService(FoodOrderRepository foodOrderRepository, ModelMapper modelMapper) {
        this.foodOrderRepository = foodOrderRepository;
    }
    public void persistFoodOrder(FoodOrder foodOrder) {
        FoodOrder persistedFoodOrder = foodOrderRepository.save(foodOrder);
        log.info("Food order persisted: {}", persistedFoodOrder);
    }
}
