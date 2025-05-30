package com.prueba_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba_backend.config.TestConfig;
import com.prueba_backend.domain.dto.CancionDTO;
import com.prueba_backend.domain.dto.ListaReproduccionDTO;
import com.prueba_backend.exceptions.ListaReproduccionNotFoundException;
import com.prueba_backend.services.IListaReproduccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ListaReproduccionController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(TestConfig.class)
class ListaReproduccionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IListaReproduccionService listaReproduccionService;

    private ListaReproduccionDTO listaReproduccionDTO;
    private List<ListaReproduccionDTO> listasReproduccion;

    @BeforeEach
    void setUp() {
        CancionDTO cancion = new CancionDTO();
        cancion.setTitulo("Test Song");
        cancion.setArtista("Test Artist");
        cancion.setAlbum("Test Album");
        cancion.setAnno("2024");
        cancion.setGenero("Test Genre");
        cancion.setDuracion(180);

        listaReproduccionDTO = new ListaReproduccionDTO();
        listaReproduccionDTO.setId(1L);
        listaReproduccionDTO.setNombre("Test Playlist");
        listaReproduccionDTO.setDescripcion("Test Description");
        listaReproduccionDTO.setCanciones(Arrays.asList(cancion));

        listasReproduccion = Arrays.asList(listaReproduccionDTO);
    }

    @Test
    void crearListaReproduccion_Success() throws Exception {
        when(listaReproduccionService.crearListaReproduccion(any(ListaReproduccionDTO.class)))
                .thenReturn(listaReproduccionDTO);

        mockMvc.perform(post("/api/v1/playlists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(listaReproduccionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Test Playlist"))
                .andExpect(jsonPath("$.descripcion").value("Test Description"))
                .andExpect(jsonPath("$.canciones[0].titulo").value("Test Song"));

        verify(listaReproduccionService).crearListaReproduccion(any(ListaReproduccionDTO.class));
    }

    @Test
    void obtenerTodasLasListas_Success() throws Exception {
        when(listaReproduccionService.obtenerTodasLasListas()).thenReturn(listasReproduccion);

        mockMvc.perform(get("/api/v1/playlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Test Playlist"))
                .andExpect(jsonPath("$[0].descripcion").value("Test Description"))
                .andExpect(jsonPath("$[0].canciones[0].titulo").value("Test Song"));

        verify(listaReproduccionService).obtenerTodasLasListas();
    }

    @Test
    void obtenerListaPorId_Success() throws Exception {
        when(listaReproduccionService.obtenerListaPorId(anyLong())).thenReturn(listaReproduccionDTO);

        mockMvc.perform(get("/api/v1/playlists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Test Playlist"))
                .andExpect(jsonPath("$.descripcion").value("Test Description"))
                .andExpect(jsonPath("$.canciones[0].titulo").value("Test Song"));

        verify(listaReproduccionService).obtenerListaPorId(1L);
    }

    @Test
    void obtenerListaPorId_NotFound() throws Exception {
        when(listaReproduccionService.obtenerListaPorId(anyLong()))
                .thenThrow(new ListaReproduccionNotFoundException("Lista de reproducción no encontrada"));

        mockMvc.perform(get("/api/v1/playlists/1"))
                .andExpect(status().isNotFound());

        verify(listaReproduccionService).obtenerListaPorId(1L);
    }

    @Test
    void eliminarLista_Success() throws Exception {
        doNothing().when(listaReproduccionService).eliminarLista(anyLong());

        mockMvc.perform(delete("/api/v1/playlists/1"))
                .andExpect(status().isNoContent());

        verify(listaReproduccionService).eliminarLista(1L);
    }

    @Test
    void eliminarLista_NotFound() throws Exception {
        doThrow(new ListaReproduccionNotFoundException("Lista de reproducción no encontrada"))
                .when(listaReproduccionService).eliminarLista(anyLong());

        mockMvc.perform(delete("/api/v1/playlists/1"))
                .andExpect(status().isNotFound());

        verify(listaReproduccionService).eliminarLista(1L);
    }
} 