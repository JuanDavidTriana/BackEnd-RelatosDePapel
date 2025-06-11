# Eureka Server

Este módulo es el Service Discovery de la arquitectura. Permite que los microservicios se registren y descubran entre sí.

## ¿Cómo levantarlo?

```sh
cd BackEnd-RelatosDePapel/eureka-server
mvn clean spring-boot:run
```

## Acceso
- Dashboard: [http://localhost:8761](http://localhost:8761)

## Notas
- No expone endpoints de negocio.
- Debe estar levantado antes que los demás servicios. 