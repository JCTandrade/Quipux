package com.prueba_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaReproduccionDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<CancionDTO> canciones;
} 