# Proyecto Cinemax - Sistema de Gestión de Cines basado en Microservicios

Desarrollada como parte del proyecto de arquitectura de sistemas distribuidos para la asignatura **Desarrollo Fullstack**. El ecosistema completo está diseñado bajo el patrón de microservicios independientes empleando **Spring Boot**, **Java**, **OpenFeign** y persistencia relacional aislada por dominio  con **MySQL** y **Laragon (HeidiSQL)**. Tambien incluye archivos *.sql* para el funcionamiento de las bases de datos, mediante **Flyway Migration**.

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

GET http://localhost:8081/api/v1/peliculas    

GET http://localhost:8081/api/v1/peliculas/{id} -> **Mostrar Pelicula con el id Indicado**   

GET http://localhost:8081/api/v1/peliculas/filtraRating/{Rating} -> **Filtrado de Peliculas en base a su Rating (ATP, +16, +18)** 

POST http://localhost:8081/api/v1/peliculas/guardar         

PUT http://localhost:8081/api/v1/peliculas/{id}       

DELETE http://localhost:8081/api/v1/peliculas/{id} 

* **`ms-sucursal`** -> *Contiene el @ManyToOne & @OneToMany (Sala y Cine)*

GET http://localhost:8082/api/v1/cines 

GET http://localhost:8082/api/v1/cines/{id}

GET http://localhost:8082/api/v1/cines/ciudad/{ciudad} -> **Mostrar cines en base a su ciudad (puede ser comuna)**

POST http://localhost:8082/api/v1/cines 

PUT http://localhost:8082/api/v1/cines/1 

DELETE http://localhost:8082/api/v1/cines/{id}

POST http://localhost:8082/api/v1/cines/{cineId}/salas -> **Crear sala (Nombre sala y capacidad) + unir a cine**

GET http://localhost:8082/api/v1/salas

GET http://localhost:8082/api/v1/salas/{id}

GET http://localhost:8082/api/v1/salas/cine/{cineId} -> **Buscar salas segun su cineId**

PUT http://localhost:8082/api/v1/salas/{id} 

DELETE http://localhost:8082/api/v1/salas/{id}

* **`ms-cartelera`** 

GET http://localhost:8083/api/v1/funciones

GET http://localhost:8083/api/v1/funciones/{id}

GET http://localhost:8083/api/v1/funciones/pelicula/{peliculaId} -> **Buscar Funcion en base a su peliculaId**

POST http://localhost:8083/api/v1/funciones 

PUT http://localhost:8083/api/v1/funciones/{id}

DELETE http://localhost:8083/api/v1/funciones/{id}

* **`ms-usuario`** 

POST http://localhost:8084/api/v1/users/register

GET http://localhost:8084/api/v1/users/{id}

PUT http://localhost:8084/api/v1/users/{id} -> **Crear usuario (nombre, segundoNombre, apellido, correo, telefono, rut)**

DELETE http://localhost:8084/api/v1/users/{id}

* **`ms-butacas`** 

GET http://localhost:8085/api/v1/seats/show/{funcionId}?salaId={id} -> **Mostrar los asientos dependiento de la funcionId y salaId**

PUT http://localhost:8085/api/v1/seats/{id}/lock -> **bloquear (tiempo limitado) un asiento segun su id**

PUT http://localhost:8085/api/v1/seats/{id}/book -> **reservar la butaca**

* **`ms-reserva`** 

POST http://localhost:8086/api/v1/bookings -> **Crear Reserva (usuarioId, funcionId, butacaId, metodoPago, monto)**

GET http://localhost:8086/api/v1/bookings

GET http://localhost:8086/api/v1/bookings/{id}

GET http://localhost:8086/api/v1/bookings/user/{id} -> **Obtener historial de usuario (id) y sus reservas**

PUT http://localhost:8086/api/v1/bookings/{id}?nuevoEstado={estadoPago} -> **Actualizar estadoPago con su id**

DELETE http://localhost:8086/api/v1/bookings/1

* **`ms-pago`** 

**PARA RESERVAS** -> Metodos aptos para guardar un pago con la *reservaId*

POST http://localhost:8087/api/v1/pagos/pagar/reserva -> **Crear un pago desde Reserva**

GET http://localhost:8087/api/v1/pagos/reserva/{id}

**PARA CONFITERIA** -> Metodos aptos para guardar un pago con el *comboId*

POST http://localhost:8087/api/v1/pagos/pagar/combo -> **Crear un pago desde combo**

GET http://localhost:8087/api/v1/pagos/combo/{id}

* **`ms-confiteria`** 

GET http://localhost:8088/api/v1/confiteria 

GET http://localhost:8088/api/v1/confiteria/categoria/{categoria} -> **Listar dulces segun categoria**

POST http://localhost:8088/api/v1/confiteria -> **Crear combo (nombre, valor, stock, categoria)**

PUT http://localhost:8088/api/v1/confiteria/{id}

DELETE http://localhost:8088/api/v1/confiteria/{id}

POST http://localhost:8088/api/v1/confiteria/comprar/{comboId}?metodoPag={metodoPago} -> **Comprar combo y anotar transaccion en ms-pago**

GET http://localhost:8088/api/v1/confiteria/comprobar-pago/{id}

* **`ms-tickets`** 

POST http://localhost:8089/api/v1/tickets/generate -> **Crea un ticket (reservaId, estado)**

GET http://localhost:8089/api/v1/tickets/{codigo}/validate 

* **`ms-notificacion`**

POST http://localhost:8090/api/v1/notifications/enviar -> **Envia notificacion al usuario con su entrada (correoDestino, funcionId, codigoTicket)** 
