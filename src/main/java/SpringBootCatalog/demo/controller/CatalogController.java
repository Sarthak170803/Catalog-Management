package SpringBootCatalog.demo.controller;

import SpringBootCatalog.demo.model.Catalog;
import SpringBootCatalog.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<Catalog>> getAllCatalogs() {
        try {
            List<Catalog> catalogs = catalogService.getAllCatalogs();
//            if (catalogs.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
            return new ResponseEntity<>(catalogs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> getCatalogById(@PathVariable Long id) {
        Optional<Catalog> catalogData = catalogService.getCatalogById(id);
        if (catalogData.isPresent()) {
            return new ResponseEntity<>(catalogData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Catalog> createCatalog(@RequestBody Catalog catalog) {
        try {
            Catalog _catalog = catalogService.createCatalog(catalog);
            return new ResponseEntity<>(_catalog, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catalog> updateCatalog(@PathVariable Long id, @RequestBody Catalog catalog) {
        try {
            Catalog updatedCatalog = catalogService.updateCatalog(id, catalog);
            return new ResponseEntity<>(updatedCatalog, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCatalog(@PathVariable Long id) {
        try {
            catalogService.deleteCatalog(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
