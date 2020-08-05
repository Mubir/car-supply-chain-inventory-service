package com.mubir.inventory.repositories;

import com.mubir.inventory.domain.CarInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarInventoryRepository extends JpaRepository<CarInventory, UUID> {
    List<CarInventory> findAllByCarId(UUID carId);
}
