package io.pivotal.pivmart.catalog;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Catalog {
    private UUID id;
    private String name;
    private String key;
}
