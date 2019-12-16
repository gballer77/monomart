package io.pivotal.pivmart;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllByCatalog(String anyString);
}
