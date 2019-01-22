---------------------------------------------------------------------------------------------- DAS Final
use maceo
go


---------------------------------------------------------------------------------------------- Dropping tables
-- drop table logs
-- drop table administradores
-- drop table servicios
-- drop table tecnologias
-- drop table ult_actualizacion
-- drop table comisiones_tipo_transacciones
-- drop table transacciones
-- drop table usuarios
-- drop table tipo_transacciones
-- drop table producto_comercio
-- drop table scraper_categoria
-- drop table ofertas
-- drop table productos
-- drop table marcas
-- drop table categorias_productos
-- drop table comercios
-- drop table idiomas
go

----------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------- Create tables

create table idiomas
(
	id_idioma 			smallint		not null 		identity(1,1),
	nombre				varchar (100) 	not NULl,
	habilitado			BIT				not null	default 0,

	constraint pk__idioma__end primary key (id_idioma)
)
go
-- insert into idiomas values ('es', 1)
-- insert into idiomas	values	('en', 1)

create table idiomas_idiomas
(
	id_idioma 			smallint		not null,
	id_traduccion		smallint 	not NULl,
	traduccion			varchar (100)

	constraint pk__idioma_idioma__end primary key (id_idioma, id_traduccion)
)
go
-- insert into idiomas_idiomas values (1, 1, 'Español')
-- insert into idiomas_idiomas values (1, 2, 'Inglés')
-- insert into idiomas_idiomas values (2, 1, 'Spanish')
-- insert into idiomas_idiomas values (2, 2, 'English')
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
    habilitado          bit             not null        default 0, -- 0: False, 1: True
    
    
    constraint pk__comercio__end primary key (id_comercio),
    constraint uq__comercio__1__end unique (razon_social),
    constraint uq__comercio__2__end unique (cuit)
)
go
-- insert into comercios values ('Garbarino S.A', '20111121114', 'Blah 1234, cordoba, argentina', 'Garbarino', '1231223', 'https://www.postularse.com/media/pictures/company_1899_profile_200x150.png',
-- 1)
-- insert into comercios values ('Compumundo S.A', '20111111114', 'Blah 1234, cordoba, argentina', 'Compumundo', '1231223', 'https://dj4i04i24axgu.cloudfront.net/normi/statics/0.1.048/compumundo/images/experiencia-compumundo.png',
-- 1)
----------------------------------------------------------------------------------------------
create table categorias_productos
(
    id_categoria        smallint        not null        identity (1,1),
    nombre              varchar (255)   not null,
    image_url           varchar (500)   not null,
	habilitado          bit             not null        default 0, -- 0: False, 1: True
    
    constraint pk__categorias_productos__end primary key (id_categoria)
)
go

-- insert into categorias_productos values ('TVs', 'https://d34zlyc2cp9zm7.cloudfront.net/products/1779250837a13543779bc1f31d5c9b192475d0742a58a898f2b415923137e236.webp_500', 1)
-- insert into categorias_productos values ('Heladeras', 'https://d34zlyc2cp9zm7.cloudfront.net/products/220bf235108326e00d9b634d248ebb295050c9730db9cf05a52ac3b2fc895abb.webp_500', 1)

----------------------------------------------------------------------------------------------
-- drop table categorias_idiomas
create table categorias_idiomas
(
    id_categoria        smallint        not null,
    id_idioma           SMALLINT	    not null,
    nombre             varchar (500)   not null,
    
    constraint pk__categorias_idiomas__end primary key (id_categoria, id_idioma)
)

insert into categorias_idiomas values (1, 1, 'Televisores')
insert into categorias_idiomas values (1, 2, 'TVs')
insert into categorias_idiomas values (2, 1, 'Heladeras')
insert into categorias_idiomas values (2, 2, 'Fridges')
----------------------------------------------------------------------------------------------
create table marcas
(
    id_marca        	smallint        not null        identity (1,1),
    nombre              varchar (255)   not null 		unique,
    
    constraint pk__marca__end primary key (id_marca)
)
go

-- insert into marcas values ('LG');
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

-- insert into productos values ('Smart TV LG 43 " Full HD 43LK5700PSC', 1, '43LK5700PSC', 1)
----------------------------------------------------------------------------------------------
create table ofertas
(
    id_oferta           integer        not null        identity (1,1),
    id_comercio         smallint        not null,
    image_url           varchar (500)   not null,
    fecha_inicio        DATETIME        not null,
    fecha_fin           DATETIME        not null,
	url_oferta			varchar (500)	not null,
	precio_oferta		FLOAT			not null,
	id_producto			integer			not null,
    habilitada          BIT             not null        default 0,

    constraint pk__oferta__end primary key (id_oferta, id_comercio),
    constraint fk__oferta__end foreign key (id_comercio)
        references comercios (id_comercio)
)
go

-- insert into ofertas values (1, 'https://d34zlyc2cp9zm7.cloudfront.net/products/1779250837a13543779bc1f31d5c9b192475d0742a58a898f2b415923137e236.webp_500',
--  '2019-01-19 00:00:00', '2019-01-31 23:59:59', 'https://www.compumundo.com.ar/producto/smart-tv-lg-43-full-hd-43lk5700psc/3c66884d00', 12999, 1,1)
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

-- insert into producto_comercio values (1, 1, 20999, 'https://www.compumundo.com.ar/producto/smart-tv-lg-43-full-hd-43lk5700psc/3c66884d00',
-- '2019-01-20 19:00:00', 'https://d34zlyc2cp9zm7.cloudfront.net/products/1779250837a13543779bc1f31d5c9b192475d0742a58a898f2b415923137e236.webp_1000',
-- 1)
-- insert into producto_comercio values (1, 1, 21999, 'https://www.compumundo.com.ar/producto/smart-tv-lg-43-full-hd-43lk5700psc/3c66884d00',
-- '2019-01-20 19:00:00', 'https://d34zlyc2cp9zm7.cloudfront.net/products/1779250837a13543779bc1f31d5c9b192475d0742a58a898f2b415923137e236.webp_1000',
-- 1)


----------------------------------------------------------------------------------------------
create table tipo_transacciones
(
    id_tipo             SMALLINT        not null,
    nombre              varchar (250)   not null,

    constraint pk__tipo_transacciones__end primary key (id_tipo),
)
go

----------------------------------------------------------------------------------------------
create table usuarios
(
    id_usuario          integer         not null        identity (1,1),
    usuario             varchar (50) not null,
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
    id_producto         integer         not null,
    id_tipo_transaccion SMALLINT        not null,
    id_comercio         SMALLINT        not null,
    id_usuario          INTEGER         not null,
    id_oferta           integer,
    pending             bit             not NULL    default 0,

    constraint pk__transaccion__end primary key (id_transaccion),
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
    id_comision         smallint        not null,
    id_tipo             SMALLINT        not null,
    id_comercio         SMALLINT        not null,
    fecha_inicio        DATETIME        not null,
    fecha_fin           DATETIME        not null,
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

	constraint pk__scraper_categoria__end PRIMARY key (id_comercio, id_categoria)
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

---------------------------------------------------------------------------------------------- Check Administrador

---------------------------------------------------------------------------------------------- Validar User

---------------------------------------------------------------------------------------------- Registrar User

---------------------------------------------------------------------------------------------- Registrar Administrador

---------------------------------------------------------------------------------------------- Obtener Lista de Comercios
drop procedure getComercios
go

create procedure getComercios
as
begin
    SELECT * FROM comercios
end
go

-- execute getComercios

---------------------------------------------------------------------------------------------- Obtener Datos de Comercio
drop procedure getDatosComercio
go

create procedure getDatosComercio
(
	@idComercio smallint
)
as
begin
    SELECT co.id_comercio, razon_social, cuit, direccion, telefono, habilitado, logo_url, ua.fecha_productos, ua.fecha_ofertas
	FROM comercios co
	LEFT JOIN ult_actualizacion ua
		ON ua.id_comercio = co.id_comercio
	WHERE co.id_comercio = @idComercio
end
go

EXECUTE getDatosComercio @idComercio=3

---------------------------------------------------------------------------------------------- Obtener Datos de Comercio: Precio Comision
drop procedure getValoresComisionesComercio
go

create procedure getValoresComisionesComercio
(
	@idComercio smallint
)
as
begin
    SELECT comi.nombre, fecha_inicio, fecha_fin, valor, co.id_comercio
	FROM comercios co
	RIGHT JOIN (SELECT nombre, fecha_inicio, fecha_fin, valor, id_comercio FROM comisiones_tipo_transacciones ctt
				JOIN tipo_transacciones tt
					ON tt.nombre = ctt.id_tipo
				WHERE ctt.id_comercio = @idComercio) comi
		ON comi.id_comercio = co.id_comercio
	WHERE co.id_comercio = @idComercio
end
go

EXECUTE getValoresComisionesComercio @idComercio=3

---------------------------------------------------------------------------------------------- Guardar Comercio

---------------------------------------------------------------------------------------------- Editar Comercio

---------------------------------------------------------------------------------------------- Obtener Lista de Categorias
drop procedure getCategorias
go

create procedure getCategorias
as
begin
    SELECT cp.id_categoria, ci.nombre, image_url, cp.habilitado, idi.nombre as lang FROM categorias_productos cp
	JOIN categorias_idiomas ci
		ON cp.id_categoria = ci.id_categoria
	JOIN idiomas idi
		ON ci.id_idioma = idi.id_idioma
	WHERE cp.habilitado = 1
end
go

-- execute getCategorias


---------------------------------------------------------------------------------------------- Guardar Categoria

---------------------------------------------------------------------------------------------- Editar Categoria

---------------------------------------------------------------------------------------------- Obtener Lista de Productos x categoria
drop procedure getProductos
go

create procedure getProductos
(
	@categoria	smallint
)
as
begin
    SELECT prod.id_producto, nombre, modelo, precio, url_producto, fecha_actualizado, image_url, logo_url
	FROM productos prod
	JOIN producto_comercio prodc
		ON prodc.id_producto = prod.id_producto
	JOIN comercios co
		ON prodc.id_comercio = co.id_comercio
	WHERE prodc.habilitado = 1
	AND prod.id_categoria = @categoria
	ORDER BY prodc.precio, prod.modelo

end
go
execute getProductos @categoria = 1

---------------------------------------------------------------------------------------------- Guardar Producto
drop procedure guardarProducto
go

create procedure guardarProducto
(
	@nombre            	varchar(255),
    @marca            	varchar(255),
    @modelo            	varchar(255),
    @id_categoria      	smallint,
	@id_comercio		SMALLINT,
	@precio				float,
	@url_producto		varchar (500),
	@image_url           varchar (500)
)
as
BEGIN
-- Insertar producto en productos y luego obtener el id del producto e insertar el resto de la info en producto_comercio. Poner habilitado = 1 en ambas tablas
END
go

---------------------------------------------------------------------------------------------- Update Producto
-- Ver como hacer aca con los productos que tienen que ser deshabilitados..
-- Quizas puedo correr el scrapper, y si el producto no aparece en ninguna página, lo deshabilito
---------------------------------------------------------------------------------------------- Obtener Ofertas
drop procedure getOfertas
go

create procedure getOfertas
as
begin
    SELECT id_oferta, ofe.image_url as oferta_img_url, fecha_fin, fecha_inicio, url_oferta, precio as precio_normal, precio_oferta, prod.id_producto, ofe.id_comercio, logo_url as comercio_logo_url, nombre
	FROM ofertas ofe
	JOIN comercios com
		ON ofe.id_comercio = com.id_comercio
	JOIN productos prod
		ON ofe.id_producto = prod.id_producto
	JOIN producto_comercio pc
		ON pc.id_comercio = ofe.id_comercio
		AND pc.id_producto = ofe.id_producto
	WHERE habilitada = 1
		
end
go

-- EXECUTE getOfertas

---------------------------------------------------------------------------------------------- Obtener Comision Total
drop procedure getComisiones
go

create procedure getComisiones
(
	@fecha_inicio	datetime,
	@fecha_fin		datetime,
	@comercio		smallint
)
as
begin
	-- Seleccionar la suma de la multiplicacion de todas las comisiones entre fi y ff de ese comercio con el valor correspondiente por tipo de comision
end
go

---------------------------------------------------------------------------------------------- Obtener Comision
drop procedure getComisionPorTipo
go

create procedure getComisionPorTipo
(
	@tipo    smallint,
	@fecha_inicio	datetime,
	@fecha_fin		datetime,
	@comercio		smallint
)
as
begin
   -- Seleccionar la suma de la multiplicacion de todas las comisiones del tipo entre fi y ff de ese comercio con el valor correspondiente del tipo
end
go

---------------------------------------------------------------------------------------------- Obtener Estadistica:















