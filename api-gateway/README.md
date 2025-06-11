# API Gateway

Este módulo es la puerta de entrada a los microservicios. Se encarga de enrutar las peticiones externas a los servicios internos registrados en Eureka.

## ¿Cómo levantarlo?

```sh
cd BackEnd-RelatosDePapel/api-gateway
mvn clean spring-boot:run
```

## Endpoints
- Todos los endpoints públicos pasan por el gateway, por ejemplo: `/books`, `/payments`, etc.

## Notas
- Debe estar registrado en Eureka.
- Puedes probar los endpoints de los microservicios a través del gateway (por ejemplo, `http://localhost:8080/books`). 