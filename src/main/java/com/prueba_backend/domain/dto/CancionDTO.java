package com.prueba_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancionDTO {
    private Long id;
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;
} 