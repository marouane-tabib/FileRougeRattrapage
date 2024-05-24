package com.colome.filerouge.modules.color;

import com.colome.filerouge.entity.Color;
import com.colome.filerouge.handlers.response.ResponseMessage;
import com.colome.filerouge.modules.color.dto.ColorRequestDTO;
import com.colome.filerouge.modules.color.dto.ColorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/colors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ColorController {

    private final ColorServiceInterface colorService;

    public ColorController(ColorServiceInterface colorService) {
        this.colorService = colorService;
    }

    @GetMapping()
    public ResponseEntity<?> getColorService() {
        List<Color> colors = colorService.getAllColors();

        if(colors.isEmpty()) {
            return ResponseMessage.notFound("No color found");
        }else {
            List<ColorResponseDTO> colorResponseDTOS = new ArrayList<>();
            for(Color color : colors) {
                colorResponseDTOS.add(ColorResponseDTO.fromColor(color));
            }

            return ResponseMessage.ok(colorResponseDTOS, "Success");
        }
    }

    //get color by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getColorById(@PathVariable Long id) {
        // get the color by id
        Color color = colorService.getColorById(id);

        // convert color to color response dto
        ColorResponseDTO colorResponseDTO = ColorResponseDTO.fromColor(color);

        // return response
        return ResponseMessage.ok(colorResponseDTO, "Success");
    }

    // add color
    @PostMapping("/color")
    public ResponseEntity<?> addColor(@RequestBody ColorRequestDTO colorRequestDTO) {
        // convert color request dto to color
        Color color = ColorRequestDTO.toColor(colorRequestDTO);

        // save the color
        color = colorService.saveColor(color);

        // convert color to color response dto
        ColorResponseDTO colorResponseDTO = ColorResponseDTO.fromColor(color);

        // return response
        return ResponseMessage.created(colorResponseDTO, "Color added successfully");
    }

    // update color
    @PutMapping("/color/{id}")
    public ResponseEntity<?> updateColor(@RequestBody ColorRequestDTO colorRequestDTO, @PathVariable Long id) {
        // convert color request dto to color
        Color color = ColorRequestDTO.toColor(colorRequestDTO);

        // update the color
        Color updatedColor = colorService.updateColor(id, color);

        // convert color to color response dto
        ColorResponseDTO colorResponseDTO = ColorResponseDTO.fromColor(updatedColor);

        // return response
        return ResponseMessage.ok(colorResponseDTO, "Color updated successfully");
    }

    // delete color
    @DeleteMapping("/color/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable Long id) {
        // delete the color
        colorService.deleteColorById(id);

        // return response
        return ResponseMessage.ok(null,"Color deleted successfully");
    }
}
