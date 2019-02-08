create database maceo2
use maceo2
go
create table idiomas
(
	id_idioma 			smallint		not null 		identity(1,1),
	nombre				varchar (100) 	not NULl,
	habilitado			BIT				not null	default 0,

	constraint pk__idioma__end primary key (id_idioma),
	constraint uq__idioma__end	unique(nombre)
)
go
insert into idiomas values ('es', 1)
insert into idiomas	values	('en', 1)

create table idiomas_idiomas
(
	id_idioma 			smallint		not null,
	id_traduccion		smallint 		not NULl 		identity(1,1),
	traduccion			varchar (100)

	constraint pk__idioma_idioma__end primary key (id_idioma, id_traduccion)
    constraint fk__idioma_idioma__end foreign key (id_idioma)
        references idiomas (id_idioma)
)
go

----------------------------------------------------------------------------------------------

create table comercios
(
    id_comercio         smallint        not null        identity (1,1),
    razon_social        varchar (50)    not null,
    cuit                varchar (14)    not null,
    direccion           varchar (255)   not null,
	nombre_publico		varchar (255) 	not null,
    telefono            varchar (20)    not null,
    logo_url            varchar (500)   not null,
	cp					integer			not null,
    habilitado          bit             not null        default 0, -- 0: False, 1: True
    
    
    constraint pk__comercio__end primary key (id_comercio),
    constraint uq__comercio__1__end unique (razon_social),
    constraint uq__comercio__2__end unique (cuit)
)
go

----------------------------------------------------------------------------------------------
create table categorias_productos
(
    id_categoria        smallint        not null        identity (1,1),
    nombre              varchar (255)   not null,
	image_url			varchar (500)	not null,
	habilitado          bit             not null        default 0, -- 0: False, 1: True
    
    constraint pk__categorias_productos__end primary key (id_categoria)
)
go

----------------------------------------------------------------------------------------------
-- drop table categorias_idiomas
create table categorias_idiomas
(
    id_categoria        smallint        not null,
    id_idioma           SMALLINT	    not null,
    nombre             varchar (500)   not null,
    
    constraint pk__categorias_idiomas__end primary key (id_categoria, id_idioma),
    constraint fk__idioma__end foreign key (id_idioma)
        references idiomas (id_idioma),
    constraint fk__cateogorias__end foreign key (id_categoria)
        references categorias_productos (id_categoria)
)
go
-----------------------
create table marcas
(
    id_marca        	smallint        not null        identity (1,1),
    nombre              varchar (255)   not null 		unique,
    
    constraint pk__marca__end primary key (id_marca)
)
go

----------------------------------------------------------------------------------------------

create table productos
(
    id_producto         integer         not null        identity (1,1),
    nombre_marca        varchar (255)   not null,
    modelo              varchar (255)   not null,
    id_categoria        smallint        not null,

    constraint pk__producto__end primary key (id_producto),
    constraint fk__productos__end foreign key (id_categoria)
        references categorias_productos (id_categoria),
	-- constraint fk__productos_marca__end foreign key (nombre_marca)
    --     references marcas (id_marca),
	constraint uq__productos__end	unique(modelo, nombre_marca)
    -- Checkear que no exista otro producto de la misma marca con el mismo numero de modelo en la misma categoria
)
go

----------------------------------------------------------------------------------------------
create table ofertas
(
    id_oferta           integer        not null        identity (1,1),
    id_comercio         smallint        not null,
    image_url           varchar (500)   not null,
    fecha_inicio        DATETIME        not null,
    fecha_fin           DATETIME        not null,
	url_oferta			varchar (500)	not null,
	id_oferta_comercio	varchar (200)	not null,
	actualizada			datetime		not null,
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
	modelo_producto		varchar (500)	not null,
	nombre				varchar (500) 	not null,
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
    id_tipo             SMALLINT        not null identity(1,1),
    nombre              varchar (250)   not null,

    constraint pk__tipo_transacciones__end primary key (id_tipo),
)
go
----------------------------------------------------------------------------------------------

create table usuarios
(
    id_usuario          integer         not null        identity (1,1),
    usuario             varchar (255) not null,
    nombre              varchar (200)   not null,
    apellido            varchar (200)   not null,
    email               varchar (500)   not null,
    usuario_password    varchar (500)   not null,
    dni                 integer         not null,
    fecha_registro      DATETIME        not null        default getdate(),
	isAdmin				BIT			 	not NULL		default 0,
    
    constraint pk__usuario__end primary key (id_usuario),
    constraint uq__usuario_1__end unique (usuario),
    constraint uq__usuario_2__end unique (dni),
    constraint uq__usuario_3__end unique (email)
)
go
----------------------------------------------------------------------------------------------
create table administradores
(
    id_administrador    smallint     not null  identity (1,1),
    id_usuario          INTEGER		 not null,
    intentos_fallidos   tinyint      not null   default 0,
    bloqueado           BIT          not null   default 0,

    constraint pk__administradores__end primary key (id_administrador),
	constraint fk__administradores_user__end foreign key (id_usuario)
		references usuarios	(id_usuario),
    constraint ch__intentos_fallidos__end check (intentos_fallidos >= 0),
    constraint ch__administradores__1__end check (bloqueado in (1, 0) ) -- si, no
)
go

----------------------------------------------------------------------------------------------

create table transacciones
(
    id_transaccion      integer         not null        identity (1,1),
    fecha               DATETIME        not null        default getdate(),
    id_producto         integer,
    id_tipo_transaccion SMALLINT        not null,
    id_comercio         SMALLINT        not null,
    id_usuario          INTEGER         not null,
    id_oferta           integer,
    pending             bit             not NULL    default 0,

    constraint pk__transaccion__end primary key (id_transaccion, id_comercio),
    constraint fk__transacciones_1__end foreign key (id_producto)
        references productos (id_producto),
    constraint fk__transacciones_2__end foreign key (id_tipo_transaccion)
        references tipo_transacciones (id_tipo),
    constraint fk__transacciones_3__end foreign key (id_comercio)
        references comercios (id_comercio),
    constraint fk__transacciones_4__end foreign key (id_usuario)
        references usuarios (id_usuario),
    constraint fk__transacciones_5__end foreign key (id_oferta, id_comercio)
        references ofertas (id_oferta, id_comercio)
)
go

----------------------------------------------------------------------------------------------
create table comisiones_tipo_transacciones
(
    id_comision         smallint        not null identity(1,1),
    id_tipo             SMALLINT        not null,
    id_comercio         SMALLINT        not null,
    fecha_inicio        DATETIME        not null,
    fecha_fin           DATETIME        ,
    valor               FLOAT           not null,

    constraint pk__comision__end primary key (id_comision, id_tipo, id_comercio),
    constraint fk__comision_1__end foreign key (id_tipo)
        references tipo_transacciones (id_tipo),
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
    javaClass           varchar (50)    not null,

    constraint pk__tecnologias__end primary key (id_tecnologia),
    constraint uq__tecnologias__1__end unique (nombre)    
)
go

----------------------------------------------------------------------------------------------

create table tipo_servicios
(
    id_tipo_servicio         tinyint         not null    identity (1,1),
    nombre                   varchar(100)    not null

    constraint pk__tipo_servicios__end primary key (id_tipo_servicio)
)
go

create table servicios
(
    id_servicio         tinyint         not null    identity (1,1),
    id_comercio         smallint        not null,
    id_tipo_servicio    tinyint         not null,
    id_tecnologia       tinyint         not null,
    service_url         varchar (500)   not null,
    funcion             varchar (500)   not null,
    puerto              smallint        not null,
	auth_token			varchar (500)	not null

    constraint pk__servicios__end primary key (id_servicio),
    constraint uq__servicios__1__end unique (id_comercio),
    constraint fk__servicios_tipos__end foreign key (id_tipo_servicio)
        references tipo_servicios (id_tipo_servicio),
    constraint fk__servicios__1__end foreign key (id_comercio)
        references comercios (id_comercio),
    constraint fk__servicios__2__end foreign key (id_tecnologia)
        references tecnologias (id_tecnologia)
		
)
go

----------------------------------------------------------------------------------------------
create table logs
(
    id_log              integer         not null    identity (1,1),
    fecha               date            not null	default getdate(),
    descripcion         varchar (500)   not null,

    constraint pk__log_errores__end primary key (id_log)
)
go

----------------------------------------------------------------------------------------------
create table scraper_categoria
(
	id_comercio			SMALLINT		not NULL,
	id_categoria		smallint 		not null,
	url_scrapping		varchar (500) 	not null,

	constraint pk__scraper_categoria__end PRIMARY key (id_comercio, id_categoria),
    constraint fk__comercio__end foreign key (id_comercio)
        references comercios (id_comercio),
    constraint fk__categoria__end foreign key (id_categoria)
        references categorias_productos (id_categoria)
)
go

----------------------------------------------------------------------------------------------
create table scraper_config
(
	id_scrap_config		INTEGER			not null		identity(1,1),
	id_comercio			SMALLINT		not null,
	prop_name			varchar (255)	not null,
	class_name			varchar (255)  	not null,
	is_in_title			BIT				not null		default 0,
	needs_crawl			BIT				not null		default 0,

	constraint pk__scraper_config__end PRIMARY key (id_scrap_config, id_comercio),
    constraint fk__comercio_scraper__end foreign key (id_comercio)
        references comercios (id_comercio)
)
go
