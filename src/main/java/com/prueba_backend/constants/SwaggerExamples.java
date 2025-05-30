package com.prueba_backend.constants;

public final class SwaggerExamples {
    private SwaggerExamples() {
        // Constructor privado para evitar instanciación
    }

    public static final String PLAYLIST_CREATE_EXAMPLE = """
            {
                "nombre": "Mis Canciones Favoritas",
                "descripcion": "Una colección de mis canciones favoritas",
                "canciones": [
                    {
                        "titulo": "Canción 1",
                        "artista": "Artista 1",
                        "album": "Album 1",
                        "anno": "2024",
                        "genero": "Pop",
                        "duracion": 180
                    },
                    {
                        "titulo": "Canción 2",
                        "artista": "Artista 2",
                        "album": "Album 2",
                        "anno": "2023",
                        "genero": "Rock",
                        "duracion": 240
                    }
                ]
            }""";

    public static final String PLAYLIST_RESPONSE_EXAMPLE = """
            {
                "id": 1,
                "nombre": "Mis Canciones Favoritas",
                "descripcion": "Una colección de mis canciones favoritas",
                "canciones": [
                    {
                        "id": 1,
                        "titulo": "Canción 1",
                        "artista": "Artista 1",
                        "album": "Album 1",
                        "anno": "2024",
                        "genero": "Pop",
                        "duracion": 180
                    },
                    {
                        "id": 2,
                        "titulo": "Canción 2",
                        "artista": "Artista 2",
                        "album": "Album 2",
                        "anno": "2023",
                        "genero": "Rock",
                        "duracion": 240
                    }
                ]
            }""";
} 