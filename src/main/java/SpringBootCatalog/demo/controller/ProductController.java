package SpringBootCatalog.demo.controller;

import SpringBootCatalog.demo.model.Product;
import SpringBootCatalog.demo.service.ProductService;
import SpringBootCatalog.demo.service.CatalogService; // Import CatalogService to retrieve catalog
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CatalogService catalogService; // Autowire CatalogService

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            // Retrieve the catalog and set it in the product
            product.setCatalog(catalogService.getCatalogById(product.getCatalog().getId())
                    .orElseThrow(() -> new RuntimeException("Catalog not found")));
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            // Retrieve the catalog and set it in the product
            product.setCatalog(catalogService.getCatalogById(product.getCatalog().getId())
                    .orElseThrow(() -> new RuntimeException("Catalog not found")));
            Product updatedProduct = productService.updateProduct(id, product);
            return updatedProduct != null ?
                    ResponseEntity.ok(updatedProduct) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
