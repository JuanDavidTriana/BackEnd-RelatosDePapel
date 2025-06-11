# Backend - Relatos de Papel

Este backend está compuesto por microservicios desarrollados en Spring Boot y gestionados mediante Eureka (Service Discovery) y un API Gateway. Cada microservicio tiene su propia base de datos y lógica de negocio.

## Arquitectura
- **Eureka Server:** Descubrimiento de servicios.
- **API Gateway:** Puerta de entrada para las peticiones externas.
- **ms-books-catalogue:** Gestión de libros.
- **ms-books-payments:** Gestión de pagos.

## Requisitos
- Java 17+
- Maven 3.8+
- MySQL (para los microservicios que lo requieran)

## Levantar los servicios
Abre una terminal por cada servicio y ejecuta:

```sh
cd BackEnd-RelatosDePapel/eureka-server && mvn clean spring-boot:run
cd BackEnd-RelatosDePapel/api-gateway && mvn clean spring-boot:run
cd BackEnd-RelatosDePapel/ms-books-catalogue && mvn clean spring-boot:run
cd BackEnd-RelatosDePapel/ms-books-payments && mvn clean spring-boot:run
```

## Pruebas básicas
- Accede a Eureka: [http://localhost:8761](http://localhost:8761)
- Prueba los endpoints de cada microservicio (ver los README de cada uno para detalles)
- Usa Postman o curl para probar los endpoints REST

## Documentación adicional
Consulta los README.md de cada microservicio para detalles de endpoints y pruebas específicas. 