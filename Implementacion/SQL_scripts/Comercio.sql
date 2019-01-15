---------------------------------------------------------------------------------------------- DAS Final
use asistente_DAS
go


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
    marca               varchar (255)   not null,
    modelo              varchar (255)   not null,
    id_categoria        smallint        not null,
    id_marca            smallint        not null,

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


