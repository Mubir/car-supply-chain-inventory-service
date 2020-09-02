package com.mubir.common.events;

import com.mubir.inventory.web.model.CarOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class AllocateOrderResult {
    private CarOrderDto carOrderDto;
    private Boolean allocationError=false;
    private Boolean pendingInventory=false;
}
