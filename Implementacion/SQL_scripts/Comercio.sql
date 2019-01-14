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
    nombre              varchar (200)   not null,
    apellido            varchar (200)   not null,
    email               varchar (500)   not null,
    cliente_password    varchar (500)   not null,
    fecha_registro      DATETIME        not null        default getdate(),

    constraint pk__cliente__end primary key (id_cliente)
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
    eail                varchar (50) not null,
    nombre              varchar (50) not null,
    apellido            varchar (50) not null,
    intentos_fallidos   tinyint      not null   default 0,
    bloqueado           BIT          not null   default 0,

    constraint pk__administradores__end primary key (id_administrador),
    constraint uq__administradores__1__end unique (usuario),
    constraint uq__administradores__2__end unique (dni),
    constraint uq__administradores__3__end unique (mail),
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


