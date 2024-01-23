package io.pivotal.pivmart.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private UUID id;
    private UUID catalogId;
    private String name;
    private String price;
    private String description;
    private String imageSrc;
    private String imageAlt;
}
