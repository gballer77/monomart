package io.pivotal.pivmart.product;

import java.util.Random;
import java.util.UUID;

public class ProductFactory {
    public static Product create(UUID catalogId, String name) {
        return Product.builder()
                .id(UUID.randomUUID())
                .catalogId(catalogId)
                .name(name)
                .imageSrc(String.format("https://picsum.photos/id/%d/200/300", new Random().nextInt(100)))
                .imageAlt("Product Alt Text")
                .description(String.format("This is a description of %s", name))
                .price(String.format("%d.99", new Random().nextInt(100)))
                .build();
    }
}
