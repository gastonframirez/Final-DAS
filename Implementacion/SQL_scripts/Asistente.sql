---------------------------------------------------------------------------------------------- DAS Final
use asistente_DAS
go


---------------------------------------------------------------------------------------------- Dropping tables
drop table logs
drop table administradores
drop table servicios
drop table tecnologias
drop table ult_actualizacion
drop table comisiones_tipo_transacciones
drop table transacciones
drop table clientes
drop table tipo_transacciones
drop table producto_comercio
drop table ofertas
drop table productos
drop table categorias_productos
drop table comercios
go

----------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------- Create tables

create table comercio
(
    id_comercio         smallint        not null        identity (1,1),
    razon_social        varchar (50)    not null,
    cuit                varchar (14)    not null,
    direccion           varchar (255)   not null,
    telefono            varchar (20)    not null,
    logo_url            varchar (500)   not null,
    habilitado          bit             not null        default 0, -- 0: False, 1: True
    
    
    constraint pk__comercio__end primary key (id_concesionaria),
    constraint uq__comercio__1__end unique (razon_social),
    constraint uq__comercio__2__end unique (cuit)
)
go

----------------------------------------------------------------------------------------------
create table categorias_productos
(
    id_categoria        smallint        not null        identity (1,1),
    nombre              varchar (255)   not null,
    image_url           varchar (500)   not null,
    
    constraint pk__categorias_productos__end primary key (id_categoria)
)
go

----------------------------------------------------------------------------------------------
create table productos
(
    id_producto         integer         not null        identity (1,1),
    nombre              varchar (255)   not null,
    marca               varchar (255)   not null,
    modelo              varchar (255)   not null,
    id_categoria        smallint        not null,

    constraint pk__producto__end primary key (id_producto),
    constraint fk__productos__end foreign key (id_categoria)
        references categorias_productos (id_categoria)
    -- Checkear que no exista otro producto de la misma marca con el mismo numero de modelo en la misma categoria
)
go

----------------------------------------------------------------------------------------------
create table ofertas
(
    id_oferta           smallint        not null        identity (1,1),
    id_comercio         smallint        not null,
    image_url           varchar (500)   not null,
    fecha_inicio        DATETIME        not null,
    fecha_fin           DATETIME        not null,
    habilitada          BIT             not null        default 0,

    constraint pk__oferta__end primary key (id_oferta, id_comercio),
    constraint fk__oferta__end foreign key (id_comercio)
        references comercios (id_comercio)
)
go

----------------------------------------------------------------------------------------------
create table producto_comercio
(
    id_producto         integer   not null,
    id_comercio         SMALLINT        not null,
    precio              FLOAT           not null,
    url_producto        varchar (500)   not null,
    fecha_actualizado   DATETIME        not null,
    image_url           varchar (500)   not null,
    habilitado          bit             not null        default 0,

    constraint pk__producto_comercio__end primary key (id_producto, id_comercio),
    constraint fk__producto_comercio_1__end foreign key (id_producto)
        references productos (id_producto),
    constraint fk__producto_comercio_2__end foreign key (id_comercio)
        references comercios (id_comercio)
)
go

----------------------------------------------------------------------------------------------
create table tipo_transacciones
(
    id_tipo             SMALLINT        not null,
    id_comercio         SMALLINT        not null,
    nombre              varchar (250)   not null,

    constraint pk__tipo_transacciones__end primary key (id_tipo, id_comercio),
    constraint fk__tipo_transacciones__end foreign key (id_comercio)
        references comercios (id_comercio)
)
go

----------------------------------------------------------------------------------------------
create table clientes
(
    id_cliente          integer         not null        identity (1,1),
    usuario             varchar (50) not null,
    nombre              varchar (200)   not null,
    apellido            varchar (200)   not null,
    email               varchar (500)   not null,
    cliente_password    varchar (500)   not null,
    dni                 integer         not null,
    fecha_registro      DATETIME        not null        default getdate(),
    intentos_fallidos   tinyint      not null   default 0,
    bloqueado           char (1)     not null   default 'n',
    
    constraint pk__cliente__end primary key (id_cliente),
    constraint uq__cliente_1__end unique (usuario),
    constraint uq__cliente_2__end unique (dni),
    constraint uq__cliente_3__end unique (email),
    constraint ch__cliente_intentos_fallidos__end check (intentos_fallidos >= 0),
    constraint ch__cliente_1__end check (bloqueado in (1, 0) ) -- si, no
)
go

----------------------------------------------------------------------------------------------
create table transacciones
(
    id_transaccion      integer         not null        identity (1,1),
    fecha               DATETIME        not null        default getdate(),
    id_producto         integer         not null,
    id_tipo_transaccion SMALLINT        not null,
    id_comercio         SMALLINT        not null,
    id_cliente          INTEGER         not null,
    id_oferta           integer,
    pending             bit             not NULL    default 0,

    constraint pk__transaccion__end primary key (id_transaccion),
    constraint fk__transacciones_1__end foreign key (id_producto)
        references productos (id_producto),
    constraint fk__transacciones_2__end foreign key (id_tipo_transaccion)
        references tipo_transacciones (id_tipo_transaccion),
    constraint fk__transacciones_3__end foreign key (id_comercio)
        references comercios (id_comercio),
    constraint fk__transacciones_4__end foreign key (id_cliente)
        references clientes (id_cliente),
    constraint fk__transacciones_5__end foreign key (id_oferta)
        references ofertas (id_oferta)
)
go

----------------------------------------------------------------------------------------------
create table comisiones_tipo_transacciones
(
    id_comision         smallint        not null,
    id_tipo             SMALLINT        not null,
    id_comercio         SMALLINT        not null,
    fecha_inicio        DATETIME        not null,
    fecha_fin           DATETIME        not null,
    valor               FLOAT           not null,

    constraint pk__comision__end primary key (id_comision, id_tipo, id_comercio),
    constraint fk__comision_1__end foreign key (id_tipo)
        references tipo_transacciones (id_tipo_transaccion),
    constraint fk__comision_2__end foreign key (id_comercio)
        references comercios (id_comercio)
)
go

----------------------------------------------------------------------------------------------
create table ult_actualizacion
(
    id_comercio         SMALLINT        not null,
    fecha_productos     DATETIME        not null,
    fecha_ofertas       DATETIME        not NULL,

    constraint pk__actualizacion__end primary key (id_comercio),
    constraint fk__actualizacion__end foreign key (id_comercio)
        references comercios (id_comercio)
    
)
go

----------------------------------------------------------------------------------------------
create table tecnologias
(
    id_tecnologia       tinyint         not null    identity (1,1),
    nombre              varchar (20)    not null,
    javaCalss           varchar (50)    not null,

    constraint pk__tecnologias__end primary key (id_tecnologia),
    constraint uq__tecnologias__1__end unique (nombre)    
)
go

----------------------------------------------------------------------------------------------
create table servicios
(
    id_servicio         tinyint         not null    identity (1,1),
    id_comercio         smallint        not null,
    id_tecnologia       tinyint         not null,
    service_url         varchar (500)   not null,
    funcion             varchar (500)   not null,
    puerto              smallint        not null,

    constraint pk__servicios__end primary key (id_servicio),
    constraint uq__servicios__1__end unique (id_comercio),
    constraint fk__servicios__1__end foreign key (id_comercio)
        references comercios (id_comercio),
    constraint fk__servicios__2__end foreign key (id_tecnologia)
        references tecnologias (id_tecnologia)
)
go

----------------------------------------------------------------------------------------------
create table administradores
(
    id_administrador    smallint     not null  identity (1,1),
    usuario             varchar (50) not null,
    admin_password      varchar (50) not null,
    dni                 integer      not null,
    email                varchar (50) not null,
    nombre              varchar (50) not null,
    apellido            varchar (50) not null,
    intentos_fallidos   tinyint      not null   default 0,
    bloqueado           BIT          not null   default 0,

    constraint pk__administradores__end primary key (id_administrador),
    constraint uq__administradores__1__end unique (usuario),
    constraint uq__administradores__2__end unique (dni),
    constraint uq__administradores__3__end unique (email),
    constraint ch__intentos_fallidos__end check (intentos_fallidos >= 0),
    constraint ch__administradores__1__end check (bloqueado in (1, 0) ) -- si, no
)
go

----------------------------------------------------------------------------------------------
create table logs
(
    id_log              integer         not null    identity (1,1),
    fecha               date            not null	default getdate(),
    descripcion         varchar (500)   not null,

    constraint pk__log_errores__end primary key (id_error)
)
go

----------------------------------------------------------------------------------------------

create table provincias
(
    id_provincia    tinyint         not null    identity (1,1),
    nombre          varchar (50)    not null,

    constraint pk__provincias__end primary key (id_provincia),
    constraint uq__provincias__end unique (nombre)
)
go

----------------------------------------------------------------------------------------------

delete from provincias
go

insert into provincias (nombre)
values
	('Buenos Aires'),
	('Catamarca'),
	('Chaco'),
	('Chubut'),
	('Cordoba'),
	('Corrientes'),
	('Entre Rios'),
	('Formosa'),
	('Jujuy'),
	('La Pampa'),
	('La Rioja'),
	('Mendoza'),
	('Misiones'),
	('Neuquen'),
	('Rio Negro'),
	('Salta'),
	('San Juan'),
	('San Luis'),
	('Santa Cruz'),
	('Santa Fe'),
	('Santiago del Estero'),
	('Tierra del Fuego'),
	('Tucuman')
go

----------------------------------------------------------------------------------------------
create table localidades
(
    id_provincia    tinyint         not null,
    id_localidad    smallint        not null,
    nombre          varchar (50)    not null,

    constraint pk__localidades__end primary key (id_provincia, id_localidad),
    constraint fk__localidades__provincias__1__end foreign key (id_provincia)
        references provincias (id_provincia),
    constraint uq__localidades__1__end unique (id_provincia, nombre)
)
go

-----------------------------------------------------------------------

delete from localidades
go

insert into localidades (id_provincia,id_localidad,nombre)
values
	(1,1,'25 de Mayo'),
	(1,2,'9 de Julio'),
	(1,3,'Adolfo Alsina'),
	(1,4,'Adolfo Gonzales Chaves'),
	(1,5,'Alberti'),
	(1,6,'Almirante Brown'),
	(1,7,'Arrecifes'),
	(1,8,'Avellaneda'),
	(1,9,'Ayacucho'),
	(1,10,'Azul'),
	(1,11,'Bahia Blanca'),
	(1,12,'Balcarce'),
	(1,13,'Baradero'),
	(1,14,'Benito Juarez'),
	(1,15,'Berazategui'),
	(1,16,'Berisso'),
	(1,17,'Bolivar'),
	(1,18,'Bragado'),
	(1,19,'Brandsen'),
	(1,20,'Ca�uelas'),
	(1,21,'Campana'),
	(1,22,'Capitan Sarmiento'),
	(1,23,'Carlos Casares'),
	(1,24,'Carlos Tejedor'),
	(1,25,'Carmen de Areco'),
	(1,26,'Castelli'),
	(1,27,'Chacabuco'),
	(1,28,'Chascomus'),
	(1,29,'Chivilcoy'),
	(1,30,'Colon'),
	(1,31,'Coronel de Marina L. Rosales'),
	(1,32,'Coronel Dorrego'),
	(1,33,'Coronel Pringles'),
	(1,34,'Coronel Suarez'),
	(1,35,'Daireaux'),
	(1,36,'Dolores'),
	(1,37,'Ensenada'),
	(1,38,'Escobar'),
	(1,39,'Esteban Echeverria'),
	(1,40,'Exaltacion de la Cruz'),
	(1,41,'Ezeiza'),
	(1,42,'Florencio Varela'),
	(1,43,'Florentino Ameghino'),
	(1,44,'General Alvarado'),
	(1,45,'General Alvear'),
	(1,46,'General Arenales'),
	(1,47,'General Belgrano'),
	(1,48,'General Guido'),
	(1,49,'General Juan Madariaga'),
	(1,50,'General La Madrid'),
	(1,51,'General Las Heras'),
	(1,52,'General Lavalle'),
	(1,53,'General Paz'),
	(1,54,'General Pinto'),
	(1,55,'General Pueyrredon'),
	(1,56,'General Rodriguez'),
	(1,57,'General San Martin'),
	(1,58,'General Viamonte'),
	(1,59,'General Villegas'),
	(1,60,'Guamini'),
	(1,61,'Hipolito Yrigoyen'),
	(1,62,'Hurlingham'),
	(1,63,'Ituzaingo'),
	(1,64,'Jose C. Paz'),
	(1,65,'Junin'),
	(1,66,'La Costa'),
	(1,67,'La Matanza'),
	(1,68,'La Plata'),
	(1,69,'Lanus'),
	(1,70,'Laprida'),
	(1,71,'Las Flores'),
	(1,72,'Leandro N. Alem'),
	(1,73,'Lincoln'),
	(1,74,'Loberia'),
	(1,75,'Lobos'),
	(1,76,'Lomas de Zamora'),
	(1,77,'Lujan'),
	(1,78,'Magdalena'),
	(1,79,'Maipu'),
	(1,80,'Malvinas Argentinas'),
	(1,81,'Mar Chiquita'),
	(1,82,'Marcos Paz'),
	(1,83,'Mercedes'),
	(1,84,'Merlo'),
	(1,85,'Monte'),
	(1,86,'Monte Hermoso'),
	(1,87,'Moron'),
	(1,88,'Moreno'),
	(1,89,'Navarro'),
	(1,90,'Necochea'),
	(1,91,'Olavarria'),
	(1,92,'Patagones'),
	(1,93,'Pehuajo'),
	(1,94,'Pellegrini'),
	(1,95,'Pergamino'),
	(1,96,'Pila'),
	(1,97,'Pilar'),
	(1,98,'Pinamar'),
	(1,99,'Presidente Peron'),
	(1,100,'Puan'),
	(1,101,'Punta Indio'),
	(1,102,'Quilmes'),
	(1,103,'Ramallo'),
	(1,104,'Rauch'),
	(1,105,'Rivadavia'),
	(1,106,'Rojas'),
	(1,107,'Roque Perez'),
	(1,108,'Saavedra'),
	(1,109,'Saladillo'),
	(1,110,'Salliquelo'),
	(1,111,'Salto'),
	(1,112,'San Andres de Giles'),
	(1,113,'San Antonio de Areco'),
	(1,114,'San Cayetano'),
	(1,115,'San Fernando'),
	(1,116,'San Isidro'),
	(1,117,'San Miguel'),
	(1,118,'San Nicolas'),
	(1,119,'San Pedro'),
	(1,120,'San Vicente'),
	(1,121,'Suipacha'),
	(1,122,'Tandil'),
	(1,123,'Tapalque'),
	(1,124,'Tigre'),
	(1,125,'Tordillo'),
	(1,126,'Tornquist'),
	(1,127,'Trenque Lauquen'),
	(1,128,'Tres Arroyos'),
	(1,129,'Tres de Febrero'),
	(1,130,'Tres Lomas'),
	(1,131,'Vicente Lopez'),
	(1,132,'Villa Gesell'),
	(1,133,'Villarino'),
	(1,134,'Zarate'),
	(5,1,'Calamuchita'),
	(5,2,'Capital'),
	(5,3,'Colon'),
	(5,4,'Cruz del Eje'),
	(5,5,'General Roca'),
	(5,6,'General San Martin'),
	(5,7,'Ischilin'),
	(5,8,'Juarez Celman'),
	(5,9,'Marcos Juarez'),
	(5,10,'Minas'),
	(5,11,'Pocho'),
	(5,12,'Presidente Roque Saenz Pe�a'),
	(5,13,'Punilla'),
	(5,14,'Rio Cuarto'),
	(5,15,'Rio Primero'),
	(5,16,'Rio Seco'),
	(5,17,'Rio Segundo'),
	(5,18,'San Alberto'),
	(5,19,'San Javier'),
	(5,20,'San Justo'),
	(5,21,'Santa Maria'),
	(5,22,'Sobremonte'),
	(5,23,'Tercero Arriba'),
	(5,24,'Totoral'),
	(5,25,'Tulumba'),
	(5,26,'Union')
go

----------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------- Create procedures


---------------------------------------------------------------------------------------------- Guardar log
drop procedure save_log
go

create procedure save_log
(
    @descripcion    varchar(500)
)
as
begin

    insert into logs(descripcion)
    values(@descripcion)
    
end
go


