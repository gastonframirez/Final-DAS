---------------------------------------------------------------------------------------------- DAS Final
-- create database XXXX
-- GO

-- use garbarino
-- go


---------------------------------------------------------------------------------------------- Dropping tables
drop table logs
drop table transacciones
drop table ofertas
drop table productos
drop table marcas
drop table categorias_productos
go

----------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------- Create tables

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
    -- Checkear que no exista otro producto de la misma marca con el mismo numero de modelo en la misma categoria
)
go

----------------------------------------------------------------------------------------------
create table ofertas
(
    id_oferta           smallint        not null        identity (1,1),
    fecha_inicio        DATETIME        not null,
    fecha_fin           DATETIME        not null,
    precio_oferta       FLOAT           not null,
    id_producto         integer         not null,
    habilitada          BIT             not null        default 0,

    constraint pk__oferta__end primary key (id_oferta),
    constraint fk__oferta_producto__end foreign key (id_producto)
        references productos (id_producto)
)
go

----------------------------------------------------------------------------------------------

create table transacciones
(
    id_transaccion      integer         not null        identity (1,1),
    fecha               DATETIME        not null        default getdate(),
    id_producto         integer         not null,
    id_oferta           smallint,
    tipo_transaccion  VARCHAR(100)    not null,
    nombre_cliente      VARCHAR(200)    not null,
    apellido_cliente    varchar (200)   not null,
    email_cliente       varchar (500)   not null,
    dni                 integer         not null,
    precio_producto     FLOAT           not null,

    constraint pk__transaccion__end primary key (id_transaccion),
    constraint fk__transaccion_producto__end foreign key (id_producto)
        references productos(id_producto),
    constraint fk__transaccion_oferta__end foreign key (id_oferta)
        references ofertas(id_oferta)
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
---------------------------------------------------------------------------------------------- Create procedures


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
    SELECT fecha_inicio, fecha_fin, precio_oferta, modelo 
        FROM ofertas
            JOIN productos
            ON productos.id_producto = ofertas.id_producto
        WHERE ofertas.habilitada = 1

END

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
    @tipoTransaccion    varchar (2),
    @modeloProducto     varchar (500),
    @precioProducto     float,
    @idOferta           integer
)
as
BEGIN
    declare @idProducto integer

    select @idProducto = id_producto
    from productos
    where modelo = @modeloProducto

    insert into transacciones (fecha,
                                id_producto,
                                id_oferta,
                                tipo_transaccion,
                                nombre_cliente,
                                apellido_cliente,
                                email_cliente,
                                dni,
                                precio_producto)
    values (@fechaTransaccion,
            @idProducto,
            @idOferta,
            @tipoTransaccion,
            @nombreCliente,
            @apellidoCliente,
            @emailCliente,
            @dniCliente,
            @precioProducto)

    
END

EXECUTE getOfertas