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

    public static final UUID CAR_1_UUID = UUID.fromString("7e960022-1ac9-4b47-a816-225ea923c07e");
    public static final UUID CAR_2_UUID = UUID.fromString("805f044b-7464-4c95-87e0-721e1573f06e");
    public static final UUID CAR_3_UUID = UUID.fromString("e8bd3e30-60c2-495a-a359-1173e5c1c903");

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
                .upc(CAR_1_UPC)
                .carId(CAR_1_UUID)
                .quantityOnHand(50)
                .build());
        //carInventoryRepository.findByCarUpc("0a818933-087d-47f2-ad83-2f986ed087eb");
        carInventoryRepository.save(CarInventory
                .builder()
                .upc(CAR_2_UPC)
                .carId(CAR_2_UUID)
                .quantityOnHand(50)
                .build());

        carInventoryRepository.save(CarInventory
                .builder()
                .upc(CAR_3_UPC)
                .carId(CAR_3_UUID)
                .quantityOnHand(50)
                .build());

       // log.error("** bLoaded Inventory. Record count: " + carInventoryRepository.findByU(CAR_2_UPC));
    }
}
