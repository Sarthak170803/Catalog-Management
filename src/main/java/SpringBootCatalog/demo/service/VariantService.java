package SpringBootCatalog.demo.service;

import SpringBootCatalog.demo.model.Variant;
import SpringBootCatalog.demo.repo.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepository;

    public List<Variant> getAllVariants() {
        return variantRepository.findAll();
    }

    public Optional<Variant> getVariantById(Long id) {
        return variantRepository.findById(id);
    }

    public Variant createVariant(Variant variant) {
        return variantRepository.save(variant);
    }

    public Variant updateVariant(Long id, Variant variant) {
        if (variantRepository.existsById(id)) {
            variant.setId(id);
            return variantRepository.save(variant);
        }
        return null;
    }

    public void deleteVariant(Long id) {
        variantRepository.deleteById(id);
    }
}
