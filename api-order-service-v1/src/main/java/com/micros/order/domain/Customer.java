package com.micros.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

// pendiente algo
@Data
@AllArgsConstructor(staticName = "of")
public class Customer {

    private Long id;
    private String name;

}
