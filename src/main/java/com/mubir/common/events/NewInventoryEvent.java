package com.mubir.common.events;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends CarEvent{
    public NewInventoryEvent(CarDto carDto)
    {
        super(carDto);
    }
}
