package SpringBootCatalog.demo.mapper;

import SpringBootCatalog.demo.model.Product;
import SpringBootCatalog.demo.model.Variant;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static Product toEntity(Product product) {
        return product;
    }
}
