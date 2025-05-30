# API de Listas de Reproducción

API REST desarrollada en Spring Boot para gestionar listas de reproducción de música.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- Spring Security
- Swagger/OpenAPI

## Requisitos Previos

- Java 17 o superior
- Maven
- Postman (para pruebas de API)

## Configuración del Proyecto

1. Clonar el repositorio:
```bash
git clone [URL_DEL_REPOSITORIO]
```

2. Navegar al directorio del proyecto:
```bash
cd prueba-backend
```

3. Compilar el proyecto:
```bash
./mvnw clean install
```

4. Ejecutar el proyecto:
```bash
./mvnw spring-boot:run
```

## Estructura del Proyecto

## Autenticación

La API utiliza autenticación básica (Basic Auth) con las siguientes credenciales:

- **Usuario:** `admin`
- **Contraseña:** ``

## Documentación de la API

### URL Base

### Endpoints Disponibles

#### 1. Crear una lista de reproducción
- **Método:** POST
- **URL:** `/playlists`
- **Headers:**
  - `Content-Type: application/json`
  - `Authorization: Basic YWRtaW46YWRtaW4xMjM=`
- **Body (raw, JSON):**
```json
{
  "nombre": "Mi Playlist",
  "descripcion": "Playlist de prueba",
  "canciones": [
    {
      "titulo": "Canción 1",
      "artista": "Artista 1",
      "album": "Álbum 1",
      "anno": "2024",
      "genero": "Pop"
    }
  ]
}
```
- **Respuestas:**
  - 201: Lista creada exitosamente
  - 400: Solicitud inválida
  - 401: No autorizado
  - 403: Acceso prohibido
  - 500: Error interno del servidor

#### 2. Obtener todas las listas de reproducción
- **Método:** GET
- **URL:** `/playlists`
- **Headers:**
  - `Authorization: Basic YWRtaW46YWRtaW4xMjM=`
- **Respuestas:**
  - 200: Lista de playlists obtenida exitosamente
  - 401: No autorizado
  - 403: Acceso prohibido
  - 500: Error interno del servidor

#### 3. Obtener una lista de reproducción por ID
- **Método:** GET
- **URL:** `/playlists/{id}`
  - Reemplazar `{id}` por el ID de la lista
- **Headers:**
  - `Authorization: Basic YWRtaW46YWRtaW4xMjM=`
- **Respuestas:**
  - 200: Playlist encontrada
  - 401: No autorizado
  - 403: Acceso prohibido
  - 404: Playlist no encontrada
  - 500: Error interno del servidor

#### 4. Eliminar una lista de reproducción
- **Método:** DELETE
- **URL:** `/playlists/{id}`
  - Reemplazar `{id}` por el ID de la lista
- **Headers:**
  - `Authorization: Basic YWRtaW46YWRtaW4xMjM=`
- **Respuestas:**
  - 200: Playlist eliminada exitosamente
  - 401: No autorizado
  - 403: Acceso prohibido
  - 404: Playlist no encontrada
  - 500: Error interno del servidor

## Ejemplos de Curl

### 1. Crear una lista de reproducción
```bash
curl -X POST \
  http://localhost:8080/api/v1/playlists \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Basic YWRtaW46YWRtaW4xMjM=' \
  -d '{
    "nombre": "Mi Playlist",
    "descripcion": "Playlist de prueba",
    "canciones": [
      {
        "titulo": "Canción 1",
        "artista": "Artista 1",
        "album": "Álbum 1",
        "anno": "2024",
        "genero": "Pop"
      }
    ]
  }'
```

### 2. Obtener todas las listas de reproducción
```bash
curl -X GET \
  http://localhost:8080/api/v1/playlists \
  -H 'Authorization: Basic YWRtaW46YWRtaW4xMjM='
```

### 3. Obtener una lista de reproducción por ID
```bash
curl -X GET \
  http://localhost:8080/api/v1/playlists/1 \
  -H 'Authorization: Basic YWRtaW46YWRtaW4xMjM='
```

### 4. Eliminar una lista de reproducción
```bash
curl -X DELETE \
  http://localhost:8080/api/v1/playlists/1 \
  -H 'Authorization: Basic YWRtaW46YWRtaW4xMjM='
```

## Base de Datos H2

La aplicación utiliza H2 como base de datos en memoria. Puedes acceder a la consola H2:

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Usuario:** `sa`
- **Contraseña:** (dejar en blanco)

## Seguridad

La aplicación incluye seguridad básica de Spring Security:
- Usuario por defecto: `admin`
- Contraseña: `admin123`

## Pruebas

Para probar la API, puedes:
1. Importar la colección de Postman proporcionada
2. Asegurarte de que la aplicación esté en ejecución
3. Configurar la autenticación básica en Postman con las credenciales mencionadas arriba

## Documentación Swagger/OpenAPI

La documentación completa de la API está disponible a través de Swagger UI:

- **URL:** `http://localhost:8080/swagger-ui.html`
