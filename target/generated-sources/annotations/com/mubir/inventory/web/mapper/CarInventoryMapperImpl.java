package com.mubir.inventory.web.mapper;

import com.mubir.inventory.domain.CarInventory;
import com.mubir.inventory.domain.CarInventory.CarInventoryBuilder;
import com.mubir.inventory.web.model.CarInventoryDto;
import com.mubir.inventory.web.model.CarInventoryDto.CarInventoryDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-12T15:21:59+0900",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class CarInventoryMapperImpl implements CarInventoryMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public CarInventoryDto carInventoryToCarInventoryDot(CarInventory carInventory) {
        if ( carInventory == null ) {
            return null;
        }

        CarInventoryDtoBuilder carInventoryDto = CarInventoryDto.builder();

        carInventoryDto.id( carInventory.getId() );
        carInventoryDto.createdDate( dateMapper.asOffsetDateTime( carInventory.getCreatedDate() ) );
        carInventoryDto.lastModifiedDate( dateMapper.asOffsetDateTime( carInventory.getLastModifiedDate() ) );
        carInventoryDto.carId( carInventory.getCarId() );
        carInventoryDto.quantityOnHand( carInventory.getQuantityOnHand() );

        return carInventoryDto.build();
    }

    @Override
    public CarInventory carInventoryDtoToCarDto(CarInventoryDto carInventoryDto) {
        if ( carInventoryDto == null ) {
            return null;
        }

        CarInventoryBuilder carInventory = CarInventory.builder();

        carInventory.id( carInventoryDto.getId() );
        carInventory.createdDate( dateMapper.asTimestamp( carInventoryDto.getCreatedDate() ) );
        carInventory.lastModifiedDate( dateMapper.asTimestamp( carInventoryDto.getLastModifiedDate() ) );
        carInventory.carId( carInventoryDto.getCarId() );
        carInventory.quantityOnHand( carInventoryDto.getQuantityOnHand() );

        return carInventory.build();
    }
}
