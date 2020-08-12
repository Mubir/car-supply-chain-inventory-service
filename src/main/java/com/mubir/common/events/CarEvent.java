package com.mubir.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CarEvent implements Serializable {

    static final long serialVersionUID = -3756037297000975426L;
    private CarDto carDto;
}
