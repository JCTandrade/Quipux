package com.prueba_backend.controllers;

import com.prueba_backend.constants.SwaggerConstants;
import com.prueba_backend.constants.SwaggerExamples;
import com.prueba_backend.domain.dto.ListaReproduccionDTO;
import com.prueba_backend.services.IListaReproduccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playlists")
@RequiredArgsConstructor
@Tag(name = SwaggerConstants.TAG_PLAYLIST, description = SwaggerConstants.TAG_PLAYLIST_DESCRIPTION)
public class ListaReproduccionController {

    private final IListaReproduccionService listaReproduccionService;

    @PostMapping
    @Operation(
        summary = SwaggerConstants.OPERATION_CREATE_PLAYLIST,
        description = SwaggerConstants.OPERATION_CREATE_PLAYLIST_DESC,
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ListaReproduccionDTO.class),
                examples = @ExampleObject(value = SwaggerExamples.PLAYLIST_CREATE_EXAMPLE)
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = SwaggerConstants.RESPONSE_201),
        @ApiResponse(responseCode = "400", description = SwaggerConstants.RESPONSE_400),
        @ApiResponse(responseCode = "401", description = SwaggerConstants.RESPONSE_401),
        @ApiResponse(responseCode = "403", description = SwaggerConstants.RESPONSE_403),
        @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_500)
    })
    public ResponseEntity<ListaReproduccionDTO> crearListaReproduccion(
            @Parameter(description = SwaggerConstants.PARAM_PLAYLIST_DTO) @RequestBody ListaReproduccionDTO listaReproduccionDTO) {
        return new ResponseEntity<>(listaReproduccionService.crearListaReproduccion(listaReproduccionDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
        summary = SwaggerConstants.OPERATION_GET_ALL_PLAYLISTS,
        description = SwaggerConstants.OPERATION_GET_ALL_PLAYLISTS_DESC
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = SwaggerConstants.RESPONSE_200),
        @ApiResponse(responseCode = "401", description = SwaggerConstants.RESPONSE_401),
        @ApiResponse(responseCode = "403", description = SwaggerConstants.RESPONSE_403),
        @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_500)
    })
    public ResponseEntity<List<ListaReproduccionDTO>> obtenerTodasLasListas() {
        return ResponseEntity.ok(listaReproduccionService.obtenerTodasLasListas());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = SwaggerConstants.OPERATION_GET_PLAYLIST,
        description = SwaggerConstants.OPERATION_GET_PLAYLIST_DESC
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = SwaggerConstants.RESPONSE_200),
        @ApiResponse(responseCode = "401", description = SwaggerConstants.RESPONSE_401),
        @ApiResponse(responseCode = "403", description = SwaggerConstants.RESPONSE_403),
        @ApiResponse(responseCode = "404", description = SwaggerConstants.RESPONSE_404),
        @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_500)
    })
    public ResponseEntity<ListaReproduccionDTO> obtenerListaPorId(
            @Parameter(description = SwaggerConstants.PARAM_PLAYLIST_ID) @PathVariable Long id) {
        return ResponseEntity.ok(listaReproduccionService.obtenerListaPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = SwaggerConstants.OPERATION_DELETE_PLAYLIST,
        description = SwaggerConstants.OPERATION_DELETE_PLAYLIST_DESC
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = SwaggerConstants.RESPONSE_200),
        @ApiResponse(responseCode = "401", description = SwaggerConstants.RESPONSE_401),
        @ApiResponse(responseCode = "403", description = SwaggerConstants.RESPONSE_403),
        @ApiResponse(responseCode = "404", description = SwaggerConstants.RESPONSE_404),
        @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_500)
    })
    public ResponseEntity<Void> eliminarLista(
            @Parameter(description = SwaggerConstants.PARAM_PLAYLIST_ID) @PathVariable Long id) {
        listaReproduccionService.eliminarLista(id);
        return ResponseEntity.noContent().build();
    }
} 