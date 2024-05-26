package com.colome.filerouge.modules.color;

import com.colome.filerouge.entity.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MaterialServiceTest {
    private ColorRepository colorRepository;
    private ColorService colorService;

    @BeforeEach
    void setUp() {
        colorRepository = Mockito.mock(ColorRepository.class);
        colorService = new ColorService(colorRepository);
    }

    @Test
    void saveColor() {
        Color color = Color.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(colorRepository.findByName("Tests fake name")).thenReturn(Optional.empty());
        Mockito.when(colorRepository.save(color)).thenReturn(color);

        Color savedColor = colorService.saveColor(color);
        assertNotNull(savedColor);
        assertEquals("Tests fake name", savedColor.getName());
    }

    @Test
    void updateColor() {
        Color color = Color.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Color colorUpdated = Color.builder()
                .id(color.getId())
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(colorRepository.findByName("Tests fake name")).thenReturn(Optional.of(color));
        Mockito.when(colorRepository.findById(2L)).thenReturn(Optional.of(color));
        Mockito.when(colorRepository.save(colorUpdated)).thenReturn(colorUpdated);

        Color updatedColor = colorService.updateColor(2L, colorUpdated);
        assertNotNull(updatedColor);
        assertEquals("Tests fake name", updatedColor.getName());
    }

    @Test
    void getColorById() {
        Color color = Color.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(colorRepository.findById(2L)).thenReturn(Optional.of(color));
        Color expectedColor = colorService.getColorById(2L);

        assertNotNull(expectedColor);
        assertEquals("Tests fake name", expectedColor.getName());
        assertEquals(true, expectedColor.getStatus());
    }

    @Test
    void deleteColorById() {
        Color color = Color.builder()
                .id(1L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(colorRepository.findById(1L)).thenReturn(Optional.of(color));
        colorService.deleteColorById(1L);
        Mockito.verify(colorRepository).findById(1L);
        Mockito.verify(colorRepository).deleteById(1L);
    }

    @Test
    void getAllColors() {
        Color color = Color.builder()
                .name("Tests fake name")
                .status(true).build();

        List<Color> colorList = List.of(color);
        Mockito.when(colorRepository.findAll()).thenReturn(colorList);

        List<Color> expectedColorList = colorService.getAllColors();
        assertNotNull(expectedColorList);
        assertEquals(expectedColorList.size(), colorList.size(), "Size should be 2");
        Mockito.verify(colorRepository).findAll();
    }
}