CREATE TABLE Trabajador (
id integer primary key autoincrement,
nombre varchar(60),
nif varchar(9),
nafiliacion varchar(12),
firma blob,
preciohora float,
activo boolean );
CREATE TABLE Movimiento (
dia varchar(2),
mes varchar(10),
anio varchar(4),
idTrabajador integer, horastrabajadas integer,
foreign key (idTrabajador) references Trabajador(id),
primary key (dia,mes,anio,idTrabajador) );
CREATE TABLE Sueldo (
mes varchar(10),
anio varchar(4),
precio float,
idTrabajador integer, horastrabajadasmensuales integer,
foreign key (idTrabajador) references Trabajador(id),
primary key (mes,anio,idTrabajador) );

