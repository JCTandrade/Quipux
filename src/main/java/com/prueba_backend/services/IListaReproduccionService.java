package com.prueba_backend.services;

import com.prueba_backend.domain.dto.ListaReproduccionDTO;
import java.util.List;

public interface IListaReproduccionService {
    /**
     * Crea una nueva lista de reproducción
     * @param listaReproduccionDTO Datos de la lista de reproducción a crear
     * @return ListaReproduccionDTO creada
     */
    ListaReproduccionDTO crearListaReproduccion(ListaReproduccionDTO listaReproduccionDTO);

    /**
     * Obtiene todas las listas de reproducción
     * @return Lista de ListaReproduccionDTO
     */
    List<ListaReproduccionDTO> obtenerTodasLasListas();

    /**
     * Obtiene una lista de reproducción por su ID
     * @param id ID de la lista de reproducción
     * @return ListaReproduccionDTO encontrada
     */
    ListaReproduccionDTO obtenerListaPorId(Long id);

    /**
     * Elimina una lista de reproducción por su ID
     * @param id ID de la lista de reproducción a eliminar
     */
    void eliminarLista(Long id);
} 