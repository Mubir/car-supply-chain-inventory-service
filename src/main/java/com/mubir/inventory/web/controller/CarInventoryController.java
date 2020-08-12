package com.mubir.inventory.web.controller;

import com.mubir.inventory.repositories.CarInventoryRepository;
import com.mubir.inventory.web.mapper.CarInventoryMapper;
import com.mubir.inventory.web.model.CarInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CarInventoryController {

    private final CarInventoryRepository carInventoryRepository;
    private final CarInventoryMapper carInventoryMapper;

    @GetMapping("/api/v1/car/{carId}/inventory")
    List<CarInventoryDto> listCarById(@PathVariable UUID carId)
    {
        log.warn(" calling form outside");
        log.warn(carInventoryRepository.findAllByCarId(carId)
                .stream()
                .map(carInventoryMapper::carInventoryToCarInventoryDot)
                .collect(Collectors.toList()).toString());
        return carInventoryRepository.findAllByCarId(carId)
                .stream()
                .map(carInventoryMapper::carInventoryToCarInventoryDot)
                .collect(Collectors.toList());
    }
}
