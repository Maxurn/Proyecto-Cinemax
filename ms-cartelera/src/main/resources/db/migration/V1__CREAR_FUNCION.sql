CREATE TABLE funcion (
    id int primary key auto_increment,
    pelicula_id INT NOT NULL,
    sala_id INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    precio_entrada DECIMAL(10, 2)
);