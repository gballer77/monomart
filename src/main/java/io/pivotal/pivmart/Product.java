package io.pivotal.pivmart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Product {
    private String name;
    private String catalog;
}
