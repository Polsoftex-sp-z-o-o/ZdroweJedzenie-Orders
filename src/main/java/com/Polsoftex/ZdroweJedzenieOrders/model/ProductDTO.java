package com.Polsoftex.ZdroweJedzenieOrders.model;

import lombok.Data;

import javax.persistence.Id;
import java.util.UUID;

@Data
public class ProductDTO {
    @Id
    private UUID id;
    private String name;
    private String description;
    private float price;
    private int quantity;
}
