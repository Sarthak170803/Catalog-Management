package SpringBootCatalog.demo.controller;

import SpringBootCatalog.demo.model.Variant;
import SpringBootCatalog.demo.service.VariantService;
import SpringBootCatalog.demo.service.ProductService; // Import ProductService to retrieve product
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class VariantController {
    @Autowired
    private VariantService variantService;

    @Autowired
    private ProductService productService; // Autowire ProductService

    @GetMapping
    public List<Variant> getAllVariants() {
        return variantService.getAllVariants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variant> getVariantById(@PathVariable Long id) {
        return variantService.getVariantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Variant> createVariant(@RequestBody Variant variant) {
        try {
            // Retrieve the product and set it in the variant
            variant.setProduct(productService.getProductById(variant.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found")));
            Variant createdVariant = variantService.createVariant(variant);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVariant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Variant> updateVariant(@PathVariable Long id, @RequestBody Variant variant) {
        try {
            // Retrieve the product and set it in the variant
            variant.setProduct(productService.getProductById(variant.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found")));
            Variant updatedVariant = variantService.updateVariant(id, variant);
            return updatedVariant != null ?
                    ResponseEntity.ok(updatedVariant) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
        try {
            variantService.deleteVariant(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
