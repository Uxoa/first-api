# Book API

Una API REST simple para gestionar libros almacenados en memoria.

## Endpoints disponibles

### Listar todos los libros
**GET /books**  
Devuelve una lista de libros.

### Buscar libro por ISBN
**GET /books/{isbn}**  
Devuelve un libro específico por su ISBN.

### Crear un nuevo libro
**POST /books**  
Cuerpo de la solicitud:  
```json
{
    "isbn": "A125",
    "title": "Nuevo libro",
    "author": "Nuevo autor"
}
```

### Actualizar un libro
**PUT /books/{isbn}**  
Modifica un libro existente con nuevos datos.

### Eliminar un libro
**DELETE /books/{isbn}**  
Elimina un libro por su ISBN.

## Instrucciones
1. Clona el repositorio.
2. Ejecuta la aplicación con tu IDE.
3. Usa herramientas como Postman o cURL para interactuar con la API

