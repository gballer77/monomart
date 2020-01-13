package io.pivotal.pivmart.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public
class Product {
    private UUID id;
    private UUID catalogId;
    private String name;
    private String price;
    private String description;
    private String imageSrc;
    private String imageAlt;
}
