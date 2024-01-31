package mart.mono.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean decrementProductQuantity(UUID productId, int quantity) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            Product updatedProduct = product.get();
            int currentQuantity = product.get().getQuantity();
            int newQuantity = currentQuantity - quantity;

            if (newQuantity < 0) {
                return false;
            }

            updatedProduct.setQuantity(newQuantity);
            productRepository.save(updatedProduct);
        }
        return true;
    }

    public List<Product> getForCatalog(String catalogKey) {
        return productRepository.findByCatalogId(catalogKey);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
