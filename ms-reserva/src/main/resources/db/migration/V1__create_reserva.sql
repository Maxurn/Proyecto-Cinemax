CREATE TABLE reserva (
    id int primary key auto_increment,
    usuario_id int not null,
    funcion_id int not null,
    butaca_id int not null,
    monto  double not null,
    metodo_pago  varchar(50) not null,
    estado  varchar(20) not null default 'PENDIENTE',
    fecha_reserva date  not null
);
