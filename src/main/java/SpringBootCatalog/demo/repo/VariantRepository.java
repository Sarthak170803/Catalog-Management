package SpringBootCatalog.demo.repo;

import SpringBootCatalog.demo.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Long> {
}
