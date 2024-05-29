package com.colome.filerouge.modules.material;

import com.colome.filerouge.entity.Material;
import com.colome.filerouge.handlers.response.ResponseMessage;
import com.colome.filerouge.modules.material.dto.MaterialRequestDTO;
import com.colome.filerouge.modules.material.dto.MaterialResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MaterialController {

    private final MaterialServiceInterface materialService;

    public MaterialController(MaterialServiceInterface materialService) {
        this.materialService = materialService;
    }

    @GetMapping()
    public ResponseEntity<?> getMaterialService() {
        List<Material> materials = materialService.getAllMaterials();

        if(materials.isEmpty()) {
            return ResponseMessage.notFound("No material found");
        }else {
            List<MaterialResponseDTO> materialResponseDTOS = new ArrayList<>();
            for(Material material : materials) {
                materialResponseDTOS.add(MaterialResponseDTO.fromMaterial(material));
            }

            return ResponseMessage.ok(materialResponseDTOS, "Success");
        }
    }

    //get material by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable Long id) {
        // get the material by id
        Material material = materialService.getMaterialById(id);

        // convert material to material response dto
        MaterialResponseDTO materialResponseDTO = MaterialResponseDTO.fromMaterial(material);

        // return response
        return ResponseMessage.ok(materialResponseDTO, "Success");
    }

    // add material
    @PostMapping("/material")
    public ResponseEntity<?> addMaterial(@RequestBody MaterialRequestDTO materialRequestDTO) {
        // convert material request dto to material
        Material material = MaterialRequestDTO.toMaterial(materialRequestDTO);

        // save the material
        material = materialService.saveMaterial(material);

        // convert material to material response dto
        MaterialResponseDTO materialResponseDTO = MaterialResponseDTO.fromMaterial(material);

        // return response
        return ResponseMessage.created(materialResponseDTO, "Material added successfully");
    }

    // update material
    @PutMapping("/material/{id}")
    public ResponseEntity<?> updateMaterial(@RequestBody MaterialRequestDTO materialRequestDTO, @PathVariable Long id) {
        // convert material request dto to material
        Material material = MaterialRequestDTO.toMaterial(materialRequestDTO);

        // update the material
        Material updatedMaterial = materialService.updateMaterial(id, material);

        // convert material to material response dto
        MaterialResponseDTO materialResponseDTO = MaterialResponseDTO.fromMaterial(updatedMaterial);

        // return response
        return ResponseMessage.ok(materialResponseDTO, "Material updated successfully");
    }

    // delete material
    @DeleteMapping("/material/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long id) {
        // delete the material
        materialService.deleteMaterialById(id);

        // return response
        return ResponseMessage.ok(null,"Material deleted successfully");
    }
}
