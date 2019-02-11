---------------------------------------------------------------------------------------------- DAS Final
-- create database XXXX
-- GO

-- use garbarino
-- go


---------------------------------------------------------------------------------------------- Dropping tables
-- drop table logs
-- drop table transacciones
-- drop table ofertas
-- drop table productos
-- drop table marcas
-- drop table categorias_productos
go

----------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------- Create tables
use garbarino
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
create table marcas
(
    id_marca            smallint        not null        identity (1,1),
    nombre              varchar (255)   not null,
    image_url           varchar (500)   not null,
    
    constraint pk__marcas__end primary key (id_marca)
)
go

----------------------------------------------------------------------------------------------
create table productos
(
    id_producto         integer         not null        identity (1,1),
    nombre              varchar (255)   not null,
    modelo              varchar (255)   not null,
    id_categoria        smallint        not null,
    id_marca            smallint        not null,
    precio              float           not null,
    image_url           varchar (500)   not null,

    constraint pk__producto__end primary key (id_producto),
    constraint fk__productos_1__end foreign key (id_categoria)
        references categorias_productos (id_categoria),
    constraint fk__productos_2__end foreign key (id_marca)
        references marcas (id_marca)
)
go

----------------------------------------------------------------------------------------------

create table ofertas
(
    id_oferta           smallint        not null        identity (1,1),
    fecha_inicio        DATETIME        not null,
    fecha_fin           DATETIME        not null,
    url_oferta          varchar(500)    not null,
    image_url           varchar(500)    not null,
    habilitada          BIT             not null        default 0,

    constraint pk__oferta__end primary key (id_oferta)
)
go
-- insert into ofertas values (getdate(), getdate()+100, 'https://www.compumundo.com.ar/producto/smart-tv-lg-43-full-hd-43lk5700psc/3c66884d00', 'https://d34zlyc2cp9zm7.cloudfront.net/products/1779250837a13543779bc1f31d5c9b192475d0742a58a898f2b415923137e236.webp_500', 1);
----------------------------------------------------------------------------------------------
create table transacciones
(
    id_transaccion      integer         not null        identity (1,1),
    fecha               DATETIME        not null        default getdate(),
    id_producto         integer    ,
    modelo_producto     varchar(500),
    id_oferta           smallint,
    tipo_transaccion    VARCHAR(100)    not null,
    nombre_cliente      VARCHAR(200)    not null,
    apellido_cliente    varchar (200)   not null,
    email_cliente       varchar (500)   not null,
    dni                 integer         not null,
    precio_producto     FLOAT,
    precio_comision     float           not null,

    constraint pk__transaccion__end primary key (id_transaccion),
    constraint fk__transaccion_oferta__end foreign key (id_oferta)
        references ofertas(id_oferta),
    constraint fk__transaccion_producto__end foreign key (id_producto)
        references productos(id_producto)
    -- Poner constraint por tipo y lo que puede ser null
)
go


----------------------------------------------------------------------------------------------
create table logs
(
    id_log              integer         not null    identity (1,1),
    fecha               date            not null	default getdate(),
    descripcion         varchar (500)   not null,

    constraint pk__logs__end primary key (id_log)
)
go

----------------------------------------------------------------------------------------------
create table tokens
(
    id_token            integer         not null    identity(1,1),
    hash_token          varchar(255)    not null,
    active              BIT             not null    default 0,

    constraint pk__tokens__end primary key (id_token),
    constraint uk__tokens_hash__end unique(hash_token)
)
go
-- insert into tokens values ('abbb4a0574aab5e145060b12379d88a3', 1)

----------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------- Create procedures
drop procedure validateToken
GO

create procedure validateToken
(
    @hash       varchar (255)
)
AS
BEGIN
      SELECT * 
      from tokens 
      where hash_token = @hash 
        and active = 1
END
go

---------------------------------------------------------------------------------------------- Guardar log
-- drop procedure save_log
-- go

-- create procedure save_log
-- (
--     @descripcion    varchar(500)
-- )
-- as
-- begin

--     insert into logs(descripcion)
--     values(@descripcion)
    
-- end
go


---------------------------------------------------------------------------------------------- Get Ofertas
drop procedure getOfertas
go

create procedure getOfertas
as
BEGIN
    SELECT id_oferta, fecha_inicio, fecha_fin, url_oferta, image_url 
        FROM ofertas
        WHERE ofertas.habilitada = 1

END
GO

EXECUTE getOfertas

---------------------------------------------------------------------------------------------- Save Transaccion
drop procedure saveTransaccion
go

create procedure saveTransaccion
(
    @fechaTransaccion   varchar(100),
    @nombreCliente      varchar (200),
    @apellidoCliente    varchar (200),
    @emailCliente       varchar (500),
    @dniCliente         integer,
    @tipoTransaccion    varchar (100),
    @modeloProducto     varchar (500) = null,
    @precioProducto     float = null,
    @idOferta           integer = null,
    @precioComision     float
)
as
BEGIN
    declare @idProducto integer

    select @idProducto = id_producto
    from productos
    where modelo = @modeloProducto

    insert into transacciones (fecha,
                                id_producto,
                                modelo_producto,
                                id_oferta,
                                tipo_transaccion,
                                nombre_cliente,
                                apellido_cliente,
                                email_cliente,
                                dni,
                                precio_producto,
                                precio_comision)
    values (@fechaTransaccion,
            @idProducto,
            @modeloProducto,
            @idOferta,
            @tipoTransaccion,
            @nombreCliente,
            @apellidoCliente,
            @emailCliente,
            @dniCliente,
            @precioProducto,
            @precioComision)
END
GO