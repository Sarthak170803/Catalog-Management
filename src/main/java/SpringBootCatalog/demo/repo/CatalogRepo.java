package SpringBootCatalog.demo.repo;
import SpringBootCatalog.demo.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepo extends JpaRepository<Catalog,Long> {
}
