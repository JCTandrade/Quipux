package com.prueba_backend.services;

import com.prueba_backend.domain.Cancion;
import com.prueba_backend.domain.ListaReproduccion;
import com.prueba_backend.domain.dto.CancionDTO;
import com.prueba_backend.domain.dto.ListaReproduccionDTO;
import com.prueba_backend.exceptions.ListaReproduccionNotFoundException;
import com.prueba_backend.repositories.ListaReproduccionRepository;
import com.prueba_backend.services.impl.ListaReproduccionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ListaReproduccionServiceTest {

    @Mock
    private ListaReproduccionRepository listaReproduccionRepository;

    @InjectMocks
    private ListaReproduccionServiceImpl listaReproduccionService;

    private ListaReproduccionDTO listaReproduccionDTO;
    private ListaReproduccion listaReproduccion;
    private CancionDTO cancionDTO;
    private Cancion cancion;

    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        cancionDTO = new CancionDTO();
        cancionDTO.setTitulo("Test Song");
        cancionDTO.setArtista("Test Artist");
        cancionDTO.setAlbum("Test Album");
        cancionDTO.setAnno("2024");
        cancionDTO.setGenero("Test Genre");

        listaReproduccionDTO = new ListaReproduccionDTO();
        listaReproduccionDTO.setNombre("Test Playlist");
        listaReproduccionDTO.setDescripcion("Test Description");
        listaReproduccionDTO.setCanciones(Arrays.asList(cancionDTO));

        cancion = new Cancion();
        cancion.setTitulo("Test Song");
        cancion.setArtista("Test Artist");
        cancion.setAlbum("Test Album");
        cancion.setAnno("2024");
        cancion.setGenero("Test Genre");

        listaReproduccion = new ListaReproduccion();
        listaReproduccion.setId(1L);
        listaReproduccion.setNombre("Test Playlist");
        listaReproduccion.setDescripcion("Test Description");
        listaReproduccion.setCanciones(Arrays.asList(cancion));
    }

    @Test
    void crearListaReproduccion_Success() {
        when(listaReproduccionRepository.save(any(ListaReproduccion.class))).thenReturn(listaReproduccion);

        ListaReproduccionDTO result = listaReproduccionService.crearListaReproduccion(listaReproduccionDTO);

        assertNotNull(result);
        assertEquals(listaReproduccionDTO.getNombre(), result.getNombre());
        assertEquals(listaReproduccionDTO.getDescripcion(), result.getDescripcion());
        assertEquals(1, result.getCanciones().size());
        verify(listaReproduccionRepository, times(1)).save(any(ListaReproduccion.class));
    }

    @Test
    void obtenerTodasLasListas_Success() {
        when(listaReproduccionRepository.findAll()).thenReturn(Arrays.asList(listaReproduccion));

        List<ListaReproduccionDTO> result = listaReproduccionService.obtenerTodasLasListas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(listaReproduccion.getNombre(), result.get(0).getNombre());
        verify(listaReproduccionRepository, times(1)).findAll();
    }

    @Test
    void obtenerListaPorId_Success() {
        when(listaReproduccionRepository.findById(1L)).thenReturn(Optional.of(listaReproduccion));

        ListaReproduccionDTO result = listaReproduccionService.obtenerListaPorId(1L);

        assertNotNull(result);
        assertEquals(listaReproduccion.getNombre(), result.getNombre());
        assertEquals(listaReproduccion.getDescripcion(), result.getDescripcion());
        verify(listaReproduccionRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerListaPorId_NotFound() {
        when(listaReproduccionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ListaReproduccionNotFoundException.class, () -> 
            listaReproduccionService.obtenerListaPorId(1L)
        );
        verify(listaReproduccionRepository, times(1)).findById(1L);
    }

    @Test
    void eliminarLista_Success() {
        when(listaReproduccionRepository.existsById(1L)).thenReturn(true);
        doNothing().when(listaReproduccionRepository).deleteById(1L);

        assertDoesNotThrow(() -> listaReproduccionService.eliminarLista(1L));
        verify(listaReproduccionRepository, times(1)).existsById(1L);
        verify(listaReproduccionRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminarLista_NotFound() {
        when(listaReproduccionRepository.existsById(1L)).thenReturn(false);

        assertThrows(ListaReproduccionNotFoundException.class, () -> 
            listaReproduccionService.eliminarLista(1L)
        );
        verify(listaReproduccionRepository, times(1)).existsById(1L);
        verify(listaReproduccionRepository, never()).deleteById(any());
    }
} 