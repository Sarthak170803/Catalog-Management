package SpringBootCatalog.demo.service;

import SpringBootCatalog.demo.model.Product;
import SpringBootCatalog.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CatalogService catalogService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        product.setCatalog(catalogService.getCatalogById(product.getCatalog().getId())
                .orElseThrow(() -> new RuntimeException("Catalog not found")));
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            product.setCatalog(catalogService.getCatalogById(product.getCatalog().getId())
                    .orElseThrow(() -> new RuntimeException("Catalog not found")));
            return productRepository.save(product);
        }
        throw new RuntimeException("Product not found");
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
