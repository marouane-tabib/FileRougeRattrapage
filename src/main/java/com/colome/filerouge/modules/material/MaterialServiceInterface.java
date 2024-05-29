package com.colome.filerouge.modules.material;

import com.colome.filerouge.entity.Material;

import java.util.List;

public interface MaterialServiceInterface {
    Material saveMaterial(Material material);

    Material updateMaterial(Long id, Material material);

    Material getMaterialById(Long id);

    void deleteMaterialById(Long id);
    
    List<Material> getAllMaterials();
}
