package com.prueba_backend.services.impl;

import com.prueba_backend.domain.Cancion;
import com.prueba_backend.domain.ListaReproduccion;
import com.prueba_backend.domain.dto.CancionDTO;
import com.prueba_backend.domain.dto.ListaReproduccionDTO;
import com.prueba_backend.repositories.ListaReproduccionRepository;
import com.prueba_backend.services.IListaReproduccionService;
import com.prueba_backend.exceptions.ListaReproduccionNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaReproduccionServiceImpl implements IListaReproduccionService {

    private final ListaReproduccionRepository listaReproduccionRepository;

    @Override
    @Transactional
    public ListaReproduccionDTO crearListaReproduccion(ListaReproduccionDTO listaReproduccionDTO) {
        ListaReproduccion listaReproduccion = new ListaReproduccion();
        listaReproduccion.setNombre(listaReproduccionDTO.getNombre());
        listaReproduccion.setDescripcion(listaReproduccionDTO.getDescripcion());
        
        if (listaReproduccionDTO.getCanciones() != null) {
            List<Cancion> canciones = listaReproduccionDTO.getCanciones().stream()
                .map(this::convertirACancion)
                .collect(Collectors.toList());
            canciones.forEach(cancion -> cancion.setListaReproduccion(listaReproduccion));
            listaReproduccion.setCanciones(canciones);
        }

        ListaReproduccion listaGuardada = listaReproduccionRepository.save(listaReproduccion);
        return convertirADTO(listaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListaReproduccionDTO> obtenerTodasLasListas() {
        return listaReproduccionRepository.findAll().stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ListaReproduccionDTO obtenerListaPorId(Long id) {
        ListaReproduccion listaReproduccion = listaReproduccionRepository.findById(id)
                .orElseThrow(() -> new ListaReproduccionNotFoundException("Lista de reproducción no encontrada"));
        return convertirADTO(listaReproduccion);
    }

    @Override
    @Transactional
    public void eliminarLista(Long id) {
        if (!listaReproduccionRepository.existsById(id)) {
            throw new ListaReproduccionNotFoundException("Lista de reproducción no encontrada");
        }
        listaReproduccionRepository.deleteById(id);
    }

    private ListaReproduccionDTO convertirADTO(ListaReproduccion listaReproduccion) {
        ListaReproduccionDTO dto = new ListaReproduccionDTO();
        dto.setId(listaReproduccion.getId());
        dto.setNombre(listaReproduccion.getNombre());
        dto.setDescripcion(listaReproduccion.getDescripcion());
        
        if (listaReproduccion.getCanciones() != null) {
            List<CancionDTO> cancionesDTO = listaReproduccion.getCanciones().stream()
                .map(this::convertirACancionDTO)
                .collect(Collectors.toList());
            dto.setCanciones(cancionesDTO);
        }
        
        return dto;
    }

    private CancionDTO convertirACancionDTO(Cancion cancion) {
        CancionDTO dto = new CancionDTO();
        dto.setId(cancion.getId());
        dto.setTitulo(cancion.getTitulo());
        dto.setArtista(cancion.getArtista());
        dto.setAlbum(cancion.getAlbum());
        dto.setAnno(cancion.getAnno());
        dto.setGenero(cancion.getGenero());
        dto.setDuracion(cancion.getDuracion());
        return dto;
    }

    private Cancion convertirACancion(CancionDTO dto) {
        Cancion cancion = new Cancion();
        cancion.setTitulo(dto.getTitulo());
        cancion.setArtista(dto.getArtista());
        cancion.setAlbum(dto.getAlbum());
        cancion.setAnno(dto.getAnno());
        cancion.setGenero(dto.getGenero());
        cancion.setDuracion(dto.getDuracion());
        return cancion;
    }
} 