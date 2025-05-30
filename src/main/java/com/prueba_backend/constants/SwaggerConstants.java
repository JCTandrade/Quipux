package com.prueba_backend.constants;

public final class SwaggerConstants {
    private SwaggerConstants() {
        // Constructor privado para evitar instanciación
    }

    // Tags
    public static final String TAG_PLAYLIST = "Playlist";
    public static final String TAG_PLAYLIST_DESCRIPTION = "API para gestionar listas de reproducción";

    // Operaciones
    public static final String OPERATION_CREATE_PLAYLIST = "Crear Lista de Reproducción";
    public static final String OPERATION_CREATE_PLAYLIST_DESC = "Crea una nueva lista de reproducción con sus canciones";
    
    public static final String OPERATION_GET_ALL_PLAYLISTS = "Obtener Todas las Listas";
    public static final String OPERATION_GET_ALL_PLAYLISTS_DESC = "Retorna todas las listas de reproducción disponibles";
    
    public static final String OPERATION_GET_PLAYLIST = "Obtener Lista por ID";
    public static final String OPERATION_GET_PLAYLIST_DESC = "Retorna una lista de reproducción específica por su ID";
    
    public static final String OPERATION_DELETE_PLAYLIST = "Eliminar Lista";
    public static final String OPERATION_DELETE_PLAYLIST_DESC = "Elimina una lista de reproducción por su ID";

    // Respuestas
    public static final String RESPONSE_200 = "Operación exitosa";
    public static final String RESPONSE_201 = "Recurso creado exitosamente";
    public static final String RESPONSE_400 = "Solicitud inválida";
    public static final String RESPONSE_401 = "No autorizado";
    public static final String RESPONSE_403 = "Acceso denegado";
    public static final String RESPONSE_404 = "Recurso no encontrado";
    public static final String RESPONSE_500 = "Error interno del servidor";

    // Parámetros
    public static final String PARAM_PLAYLIST_ID = "ID de la lista de reproducción";
    public static final String PARAM_PLAYLIST_DTO = "Datos de la lista de reproducción";
} 