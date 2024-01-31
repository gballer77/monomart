package mart.mono.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
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

    @OneToOne
    private Catalog catalog;

    private String name;
    private String price;
    private String description;
    private String imageSrc;
    private String imageAlt;

    private int quantity;
}
