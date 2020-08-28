package com.mubir.inventory.services;

import com.mubir.inventory.web.model.CarOrderDto;

public interface AllocationService {
    Boolean allocateOrder(CarOrderDto carOrderDto);
}
