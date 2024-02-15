package mart.mono.purchases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.catalog.Catalog;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private UUID id;

    private Catalog catalog;

    private String name;
    private String price;
    private String description;
    private String imageSrc;
    private String imageAlt;

    private int quantity;
}
