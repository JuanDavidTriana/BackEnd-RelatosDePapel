# ms-books-catalogue

Microservicio encargado de la gestión de libros.

## ¿Cómo levantarlo?

```sh
cd BackEnd-RelatosDePapel/ms-books-catalogue
mvn clean spring-boot:run
```

## Endpoints principales
- `POST /books` - Crear libro
- `GET /books` - Buscar libros (filtros opcionales)
- `PUT /books/{id}` - Actualizar libro
- `PATCH /books/{id}` - Actualización parcial
- `DELETE /books/{id}` - Eliminar libro

## Pruebas rápidas con Postman
- **GET:** `http://localhost:8081/books`
- **POST:** `http://localhost:8081/books` (ver ejemplo de body en la documentación)

## Notas
- Registrado en Eureka.
- Usa base de datos MySQL. 