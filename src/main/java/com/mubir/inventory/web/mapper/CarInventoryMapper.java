package com.mubir.inventory.web.mapper;

import com.mubir.inventory.domain.CarInventory;
import com.mubir.inventory.web.model.CarInventoryDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CarInventoryMapper {
CarInventoryDto carInventoryToCarInventoryDot(CarInventory carInventory);
CarInventory carInventoryDtoToCarDto(CarInventoryDto carInventoryDto);
}
