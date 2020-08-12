package com.mubir.inventory.services;

import com.mubir.inventory.config.JmsConfig;
import com.mubir.inventory.domain.CarInventory;
import com.mubir.common.events.NewInventoryEvent;
import com.mubir.inventory.repositories.CarInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
@Slf4j
public class NewInventoryListener {
    private final CarInventoryRepository carInventoryRepository;
    @JmsListener(destination = JmsConfig.DEV_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event)
    {
        log.warn("************************");
        log.warn(event.getCarDto().getId().toString());

        carInventoryRepository.save(CarInventory.builder()
                .carId(event.getCarDto().getId())
                .upc(event.getCarDto().getUpc())
                .quantityOnHand(event.getCarDto().getQuantityOnHand()).build()
        );
    }
}
