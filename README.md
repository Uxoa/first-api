# 📚 Book API

Una API REST simple para gestionar libros almacenados en memoria.

## 🚀 Endpoints disponibles

### 🔍 Listar todos los libros
**GET /books**  
Devuelve una lista de libros.

### 🔎 Buscar libro por ISBN
**GET /books/{isbn}**  
Devuelve un libro específico por su ISBN.

### 📝 Crear un nuevo libro
**POST /books**  
Cuerpo de la solicitud:  

```json
{
    "isbn": "A125",
    "title": "Nuevo libro",
    "author": "Nuevo autor"
}
```
### ✏️ Actualizar un libro
**PUT /books/{isbn}**  
Modifica un libro existente con nuevos datos.

### ❌ Eliminar un libro
**DELETE /books/{isbn}**  
Elimina un libro por su ISBN.

## 🛠️ Instrucciones
- 🗂️ Clona el repositorio.
- 💻 Ejecuta la aplicación con tu IDE.
- 🛠️ Usa herramientas como **Postman** para interactuar con la API.

