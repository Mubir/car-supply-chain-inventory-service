package com.mubir.inventory.services;

import com.mubir.inventory.domain.CarInventory;
import com.mubir.inventory.repositories.CarInventoryRepository;
import com.mubir.inventory.web.model.CarOrderDto;
import com.mubir.inventory.web.model.CarOrderLineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class AllocationServiceImpl implements  AllocationService{
    private final CarInventoryRepository carInventoryRepository;
    @Override
    public Boolean allocateOrder(CarOrderDto carOrderDto) {
        log.debug("Alloctiong order id:"+carOrderDto.getId());
        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        carOrderDto.getCarOrderLines().forEach(carOrderLine ->
        {
            if((((carOrderLine.getOrderQuantity() !=null?carOrderLine.getOrderQuantity():0-
                    (carOrderLine.getQuantityAllocated() !=null?carOrderLine.getQuantityAllocated():0)))>0)){
                allocateCarOrderLine(carOrderLine);
            }
            totalOrdered.set(totalOrdered.get()+carOrderLine.getOrderQuantity());
            totalAllocated.set(totalAllocated.get()+(carOrderLine.getQuantityAllocated() !=null?carOrderLine.getQuantityAllocated():0));
        });
        log.debug("Total order: "+totalOrdered.get()+" total allocated "+totalAllocated.get());
        return totalOrdered.get() == totalAllocated.get();
    }

    private void allocateCarOrderLine(CarOrderLineDto carOrderLine) {
        List<CarInventory> carInventoryList = carInventoryRepository.findAllByUpc(carOrderLine.getUpc());

        carInventoryList.forEach(carInventory -> {
            int inventory = (carInventory.getQuantityOnHand() == null) ? 0 : carInventory.getQuantityOnHand();
            int orderQty = (carOrderLine.getOrderQuantity() == null) ? 0 : carOrderLine.getOrderQuantity();
            int allocatedQty = (carOrderLine.getQuantityAllocated() == null) ? 0 : carOrderLine.getQuantityAllocated();
            int qtyToAllocate = orderQty - allocatedQty;

            if (inventory >= qtyToAllocate) { // full allocation
                inventory = inventory - qtyToAllocate;
                carOrderLine.setQuantityAllocated(orderQty);
                carInventory.setQuantityOnHand(inventory);

                carInventoryRepository.save(carInventory);
            } else if (inventory > 0) { //partial allocation
                carOrderLine.setQuantityAllocated(allocatedQty + inventory);
                carInventory.setQuantityOnHand(0);

                carInventoryRepository.delete(carInventory);
            }
        });
    }
}
