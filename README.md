# Proyecto Cinemax - Sistema de Gestión de Cines basado en Microservicios

Desarrollada como parte del proyecto de arquitectura de sistemas distribuidos para la asignatura **Desarrollo Fullstack**. El ecosistema completo está diseñado bajo el patrón de microservicios independientes empleando **Spring Boot**, **Java**, **OpenFeign** y persistencia relacional aislada por dominio  con **MySQL** y **Laragon (HeidiSQL)**.

El sistema resuelve de extremo a extremo la lógica de un complejo cinematográfico: desde la administración de películas, sucursales y salas, hasta el flujo de reservas, asignación de butacas, uso de pasarela de pago, emisión de tickets y notificaciones para el usuario final.

## Mapa de Arquitectura y Puertos de la Red

* **`ms-peliculas`** (Puerto `8081`) → `db_peliculas`
* **`ms-sucursal`** (Puerto `8082`) → `db_cine_sala`
* **`ms-cartelera`** (Puerto `8083`) → `db_cartelera`
* **`ms-usuario`** (Puerto `8084`) → `db_usuario`
* **`ms-butacas`** (Puerto `8085`) → `db_butacas`
* **`ms-reserva`** (Puerto `8086`) → `db_reserva`
* **`ms-pago`** (Puerto `8087`) → `db_pago`
* **`ms-confiteria`** (Puerto `8088`) → `db_confiteria`
* **`ms-tickets`** (Puerto `8089`) → `db_tickets`
* **`ms-notificacion`** (Puerto `8090`)

A continuación se detalla la documentación para el correcto funcionamiento mediante **Postman**

* **`ms-peliculas`**
* GET http://localhost:8081/api/v1/peliculas                        
* GET http://localhost:8081/api/v1/peliculas/{id}                         
* GET http://localhost:8081/api/v1/peliculas/filtraRating/ATP  
* POST http://localhost:8081/api/v1/peliculas/guardar            
* PUT http://localhost:8081/api/v1/peliculas/{id}                      
* DELETE http://localhost:8081/api/v1/peliculas/{id} 
* **`ms-sucursal`** 
* **`ms-cartelera`** 
* **`ms-usuario`** 
* **`ms-butacas`** 
* **`ms-reserva`** 
* **`ms-pago`** 
* **`ms-confiteria`** 
* **`ms-tickets`** 
* **`ms-notificacion`**