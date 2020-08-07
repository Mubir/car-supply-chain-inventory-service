package com.mubir.inventory.bootstrap;

import com.mubir.inventory.domain.CarInventory;
import com.mubir.inventory.repositories.CarInventoryRepository;
import com.mubir.inventory.web.mapper.CarInventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class CarInventoryBootstrap implements CommandLineRunner {
    public static final String CAR_1_UPC = "0631234200036";
    public static final String CAR_2_UPC = "0631234300019";
    public static final String CAR_3_UPC = "0083783375213";
    public static final UUID CAR_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID CAR_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID CAR_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final CarInventoryRepository carInventoryRepository;
    private final CarInventoryMapper mapper;
    @Override
    public void run(String... args) throws Exception {
        if(carInventoryRepository.count() == 0){
            loadInitialInv();
        }
    }

    private void loadInitialInv() {
        carInventoryRepository.save(CarInventory
                .builder()
                .carId(CAR_1_UUID)
                .upc(CAR_1_UPC)
                .quantityOnHand(50)
                .build());

        carInventoryRepository.save(CarInventory
                .builder()
                .carId(CAR_2_UUID)
                .upc(CAR_2_UPC)
                .quantityOnHand(50)
                .build());

        carInventoryRepository.save(CarInventory
                .builder()
                .carId(CAR_3_UUID)
                .upc(CAR_3_UPC)
                .quantityOnHand(50)
                .build());

        log.error("** bLoaded Inventory. Record count: " + carInventoryRepository.findById(CAR_2_UUID));
    }
}
