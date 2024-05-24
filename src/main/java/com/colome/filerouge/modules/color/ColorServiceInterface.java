package com.colome.filerouge.modules.color;

import com.colome.filerouge.entity.Color;

import java.util.List;

public interface ColorServiceInterface {
    Color saveColor(Color color);

    Color updateColor(Long id, Color color);

    Color getColorById(Long id);

    void deleteColorById(Long id);
    
    List<Color> getAllColors();
}
