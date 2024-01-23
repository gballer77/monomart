package io.pivotal.pivmart.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Purchase {
    private UUID id;
    private List<CartItem> items;
}
