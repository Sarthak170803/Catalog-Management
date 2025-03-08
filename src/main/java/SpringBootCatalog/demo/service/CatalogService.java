package SpringBootCatalog.demo.service;

import SpringBootCatalog.demo.model.Catalog;
import SpringBootCatalog.demo.repo.CatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepo catalogRepo;

    public List<Catalog> getAllCatalogs() {
        return catalogRepo.findAll();
    }

    public Optional<Catalog> getCatalogById(Long id) {
        return catalogRepo.findById(id);
    }

    public Catalog createCatalog(Catalog catalog) {
        return catalogRepo.save(catalog);
    }

    public Catalog updateCatalog(Long id, Catalog catalog) {
        Optional<Catalog> existingCatalog = catalogRepo.findById(id);
        if (existingCatalog.isPresent()) {
            Catalog _catalog = existingCatalog.get();
            _catalog.setName(catalog.getName());
            _catalog.setDescription(catalog.getDescription());
            _catalog.setProducts(catalog.getProducts());
            return catalogRepo.save(_catalog);
        } else {
            throw new RuntimeException("Catalog not found");
        }
    }

    public void deleteCatalog(Long id) {
        catalogRepo.deleteById(id);
    }
}
