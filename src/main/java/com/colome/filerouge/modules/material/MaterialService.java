package com.colome.filerouge.modules.material;

import com.colome.filerouge.entity.Material;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService implements MaterialServiceInterface {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material saveMaterial(Material material) {
        // check the material name
        if (materialRepository.findByName(material.getName()).isPresent()) {
            throw new ResourceNotFoundException("Material with name " + material.getName() + " already exists");
        }

        // save material
        return materialRepository.save(material);
    }

    @Override
    public Material updateMaterial(Long id, Material material) {
        // check the material name
        if (materialRepository.findByName(material.getName()).isPresent() && !materialRepository.findByName(material.getName()).get().getId().equals(id)) {
            throw new ResourceNotFoundException("Material with name " + material.getName() + " already exists");
        }

        // get material by id
        Material materialToUpdate = this.getMaterialById(id);

        // set sku, slug, category, brand
        materialToUpdate.setName(material.getName());

        // save material
        return materialRepository.save(materialToUpdate);
    }

    @Override
    public Material getMaterialById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Material with id " + id + " not found")
                );
    }

    @Override
    public void deleteMaterialById(Long id) {
        Material material = this.getMaterialById(id);
        materialRepository.delete(material);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
}
