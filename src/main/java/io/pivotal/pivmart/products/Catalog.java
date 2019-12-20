package io.pivotal.pivmart.products;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Catalog {
    private UUID id;
    private String catalogKey;
    private String displayName;
}
