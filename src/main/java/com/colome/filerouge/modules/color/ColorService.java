package com.colome.filerouge.modules.color;

import com.colome.filerouge.entity.Color;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService implements ColorServiceInterface {
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public Color saveColor(Color color) {
        // check the color name
        if (colorRepository.findByName(color.getName()).isPresent()) {
            throw new ResourceNotFoundException("Color with name " + color.getName() + " already exists");
        }

        // save color
        return colorRepository.save(color);
    }

    @Override
    public Color updateColor(Long id, Color color) {
        // check the color name
        if (colorRepository.findByName(color.getName()).isPresent() && !colorRepository.findByName(color.getName()).get().getId().equals(id)) {
            throw new ResourceNotFoundException("Color with name " + color.getName() + " already exists");
        }

        // get color by id
        Color colorToUpdate = this.getColorById(id);

        // set sku, slug, category, brand
        colorToUpdate.setName(color.getName());

        // save color
        return colorRepository.save(colorToUpdate);
    }

    @Override
    public Color getColorById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Color with id " + id + " not found")
                );
    }

    @Override
    public void deleteColorById(Long id) {
        Color color = this.getColorById(id);
        colorRepository.delete(color);
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }
}
