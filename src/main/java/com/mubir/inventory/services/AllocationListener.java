package com.mubir.inventory.services;

import com.mubir.common.events.AllocateOrderRequest;
import com.mubir.common.events.AllocateOrderResult;
import com.mubir.inventory.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListener {
    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listener(AllocateOrderRequest request)
    {
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
        builder.carOrderDto(request.getCarOrderDto());
        try
        {
            Boolean allocationResult = allocationService.allocateOrder(request.getCarOrderDto());
            if(allocationResult)
            {
                builder.pendingInventory(false);
            }else
            {
                builder.pendingInventory(true);
            }
        }catch(Exception e)
        {
            log.error(" Allocation failed for order id "+request.getCarOrderDto().getId());
            builder.allocationError(true);
        }
        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE,builder.build());
    }
}
