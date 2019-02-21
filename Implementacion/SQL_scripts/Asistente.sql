---------------------------------------------------------------------------------------------- DAS Final
-- create database maceo
-- go
use maceo
go
-------------------------------------------------------------------------------------------- Dropping tables
drop table logs
drop table administradores
drop table servicios
drop table tecnologias
drop table comisiones_tipo_transacciones
drop table transacciones
drop table usuarios
drop table tipo_transacciones
drop table producto_comercio
drop table scraper_categoria
drop table ofertas
drop table productos
drop table scraper_config
drop table categorias_idiomas
drop table categorias_productos
drop table idiomas_idiomas
drop table idiomas
drop table tipo_servicios
drop table orchestrator_config
drop table comercios
go

----------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------- Create tables

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
insert into idiomas_idiomas values (1, 'Español')
insert into idiomas_idiomas values (1, 'Inglés')
insert into idiomas_idiomas values (2, 'Spanish')
insert into idiomas_idiomas values (2,'English')
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
	totalCrawl			bit				not null 		default 0,
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
-- insert into categorias_idiomas values (1, 1, 'Televisores')
-- insert into categorias_idiomas values (1, 2, 'TVs')
-- insert into categorias_idiomas values (2, 1, 'Heladeras')
-- insert into categorias_idiomas values (2, 2, 'Fridges')
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
	constraint uq__productos__end	unique(modelo, nombre_marca)
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

insert into tipo_transacciones values ('ppClick');
insert into tipo_transacciones values ('ppOffer');
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
-- insert into usuarios values ('admin', 'Gaston', 'Ramirez', 'gastonframirez@gmail.com', '$2a$10$oPUu0ocjUy/whTdJA7CZsuN5jrv7RvBrqmIkuBWkiv6RjQ.gSce8G', 38503389, getdate(), 1);
----------------------------------------------------------------------------------------------
create table administradores
(
    id_administrador    smallint     not null  identity (1,1),
    id_usuario          INTEGER		 not null,
    intentos_fallidos   tinyint      not null   default 0,
    bloqueado           BIT          not null   default 0,
	fecha_bloqueo		datetime,

    constraint pk__administradores__end primary key (id_administrador),
	constraint fk__administradores_user__end foreign key (id_usuario)
		references usuarios	(id_usuario),
    constraint ch__intentos_fallidos__end check (intentos_fallidos >= 0),
    constraint ch__administradores__1__end check (bloqueado in (1, 0) ) -- si, no
)
go

create or alter trigger tu_ru_block_administradores
on administradores
for update
as
begin
	if (select intentos_fallidos from inserted) = 5
	begin
		update administradores
		set fecha_bloqueo = getdate(), bloqueado=1, intentos_fallidos=5
		where id_usuario = (select id_usuario from inserted)
		insert into logs values(getdate(), 'El usuario ' + convert(varchar(30),(select id_usuario from inserted)) + ' fue bloqueado luego de 5 intentos.' )
		raiserror('ERROR: usuario bloqueado luego de 5 intentos' ,16,1)
	end
	-- ELSE
	-- begin
	-- 	update administradores
	-- 	set fecha_bloqueo = (SELECT fecha_bloqueo from inserted), 
	-- 		bloqueado=(SELECT bloqueado from inserted), 
	-- 		intentos_fallidos=(SELECT intentos_fallidos from inserted)
	-- end
end
go
-- insert into administradores (id_usuario, intentos_fallidos, bloqueado) values (1, 0,0)
-- go


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
-- insert into transacciones values (getdate(), null, 2, 1, 1, 2, 1);

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

-- --------------------------------------------------------------------------------------------
-- create table ult_actualizacion
-- (
--     id_comercio         SMALLINT        not null,
--     fecha_productos     DATETIME        not null,
--     fecha_ofertas       DATETIME        not NULL,

--     constraint pk__actualizacion__end primary key (id_comercio),
--     constraint fk__actualizacion__end foreign key (id_comercio)
--         references comercios (id_comercio)
    
-- )
-- go

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
insert into tecnologias (nombre,javaClass)
values
    ('Axis 2','Axis2Client'),
    ('JAX-WS (CXF)','CXFClient'),
    ('Jersey REST','RestClient')
go
----------------------------------------------------------------------------------------------
create table tipo_servicios
(
    id_tipo_servicio         tinyint         not null    identity (1,1),
    nombre                   varchar(100)    not null

    constraint pk__tipo_servicios__end primary key (id_tipo_servicio)
)
go

insert into tipo_servicios values ('ofertas')
insert into tipo_servicios values ('transacciones')

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
    constraint uq__servicios__1__end unique (id_comercio, id_tipo_servicio),
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
	url_scrapping		varchar (500),

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
	class_name			varchar (255),
	is_in_title			BIT				not null		default 0,
	needs_crawl			BIT				not null		default 0,

	constraint pk__scraper_config__end PRIMARY key (id_scrap_config, id_comercio),
    constraint fk__comercio_scraper__end foreign key (id_comercio)
        references comercios (id_comercio)
)
go

----------------------------------------------------------------------------------------------
create table orchestrator_config
(
	id_config		integer 		not NULL		identity(1,1),
	tipo_config			INTEGER			not null, --1: Regex, 2: palabra a excluir, 3: clase css a ignorar
	palabra_ignorar		varchar(500)	not null,
	habilitado			BIT				not NULL	default(1),
	actualizado			datetime		not null,

	constraint pk__orchestrator_config__end PRIMARY key (id_config),
)
go

insert into orchestrator_config (tipo_config, palabra_ignorar, habilitado, actualizado)
values (1, '\\s(([0-9])(,||.)*)+\\s?(lts?)(.)?', 1, getdate()),
(1, '\\s(([0-9])(,||.)*)+\\s?(l?)(.)?', 1, getdate()),
(1, '\\s([0-9])+\\s?(negro?a?)', 1, getdate()),
(1, '\\s(con)*\\s*(grill)', 1, getdate()),
(1, '\\s([0-9])+\\s?"', 1, getdate()),
(1, '\\s(para)\\s[0-9]+\\s(botellas)', 1, getdate()),
(1, '\\s[0-9]+\\s(botellas)', 1, getdate()),
(1, '\\s[0-9]+\\s(bts)(.)?', 1, getdate()),
(1, '\\s(ultra)\\s(hd)', 1, getdate()),
(1, '\\s([0-9])+\\s?w', 1, getdate()),
(2, 'aluminio', 1, getdate()),
(2, 'vapor', 1, getdate()),
(2, 'premium', 1, getdate()),
(2, 'verde', 1, getdate()),
(2, 'gris', 1, getdate()),
(2, 'plateado', 1, getdate()),
(2, 'plateada', 1, getdate()),
(2, 'platinum', 1, getdate()),
(2, 'plata', 1, getdate()),
(2, 'inoxidable', 1, getdate()),
(2, 'inox.', 1, getdate()),
(2, 'inox', 1, getdate()),
(2, 'acero', 1, getdate()),
(2, 'negra', 1, getdate()),
(2, 'negro', 1, getdate()),
(2, 'blanca', 1, getdate()),
(2, 'blanco', 1, getdate()),
(2, 'azul', 1, getdate()),
(2, 'amarillo', 1, getdate()),
(2, 'amarilla', 1, getdate()),
(2, 'rojo', 1, getdate()),
(2, 'roja', 1, getdate()),
(2, 'full', 1, getdate()),
(2, 'smart', 1, getdate()),
(2, 'ultra', 1, getdate()),
(2, 'uhd', 1, getdate()),
(2, 'hd', 1, getdate()),
(2, '4k', 1, getdate()),
(2, 'tv', 1, getdate()),
(2, 'qled', 1, getdate()),
(2, 'led', 1, getdate()),
(2, 'gris', 1, getdate()),
(2, 'marfil', 1, getdate()),
(2, 'mate', 1, getdate()),
(2, 'celeste', 1, getdate()),
(2, 'clásica', 1, getdate()),
(2, 'clasica', 1, getdate()),
(2, 'silver', 1, getdate()),
(2, 'metal', 1, getdate()),
(2, 'bajo', 1, getdate()),
(2, 'mesada', 1, getdate()),
(2, 'frio', 1, getdate()),
(2, 'frío', 1, getdate()),
(2, 'directo', 1, getdate()),
(2, 'duo', 1, getdate()),
(2, 'neo', 1, getdate()),
(2, 'ciclica', 1, getdate()),
(2, 'cíclica', 1, getdate()),
(2, 'inverter', 1, getdate()),
(2, 'side', 1, getdate()),
(2, 'frost', 1, getdate()),
(2, 'no', 1, getdate()),
(2, 'ap', 1, getdate()),
(2, 'ab', 1, getdate()),
(2, 'fh', 1, getdate()),
(2, 'by', 1, getdate()),
(2, 'sbs', 1, getdate()),
(2, 'cooler', 1, getdate()),
(2, 'beer', 1, getdate()),
(2, 'netflix', 1, getdate()),
(2, 'freezers', 1, getdate()),
(2, 'freezer', 1, getdate()),
(2, 'verticales', 1, getdate()),
(2, 'cava', 1, getdate()),
(2, 'con', 1, getdate()),
(2, 'sin', 1, getdate()),
(2, '(*)', 1, getdate()),
(2, '.', 1, getdate())
go
----------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------- Create procedures


---------------------------------------------------------------------------------------------- Guardar log
create or alter procedure saveLog
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
create or alter procedure isAdmin
(
	@nombreusuario	varchar (255)
)
as
begin
    SELECT isAdmin 
	FROM usuarios
	WHERE usuario = @nombreusuario
end
go

---------------------------------------------------------------------------------------------- Validar User
create or alter procedure validateLogin
(
	@nombreusuario	varchar (255),
	@password		varchar (255)
)
as
begin
    SELECT usuario 
	FROM usuarios
	WHERE usuario = @nombreusuario
	AND usuario_password = @password
end
go

---------------------------------------------------------------------------------------------- Validar User
create or alter procedure validateLoginInverse
(
	@nombreusuario	varchar (255)
)
as
begin
    SELECT u.id_usuario, usuario, usuario_password, isAdmin, nombre, apellido, dni, email, a.bloqueado
	FROM usuarios u 
	LEFT JOIN administradores a
		ON u.id_usuario = a.id_usuario
	WHERE usuario = @nombreusuario
	
	OR email = @nombreusuario
end
go
-- execute validateLoginInverse @nombreusuario = 'gastonfra'
-- go
---------------------------------------------------------------------------------------------- Aumentar intentos Adminn
create or alter procedure increaseAdminAttempts
(
	@nombreusuario	varchar (255)
)
as
begin
	declare @idUsuario smallint;
	SET @idUsuario = (SELECT id_usuario from usuarios where usuario = @nombreusuario);

	IF (SELECT intentos_fallidos from administradores a WHERE a.id_usuario = @idUsuario)<5
		UPDATE administradores
		SET intentos_fallidos = (SELECT intentos_fallidos from administradores a WHERE a.id_usuario = @idUsuario) + 1
		WHERE id_usuario = @idUsuario
end
go

---------------------------------------------------------------------------------------------- Aumentar intentos Adminn
create or alter procedure unlockBlockedAdmins
as
begin
	UPDATE administradores set fecha_bloqueo=NULL, bloqueado = 0, intentos_fallidos = 0
	WHERE DATEDIFF(minute,fecha_bloqueo, getdate()) >= 15
end
go

-- exec  unlockBlockedAdmins
-- go
---------------------------------------------------------------------------------------------- Registrar User
create or alter procedure registerUser
(
    @nombreusuario      	varchar (255),
    @nombre             	varchar (200),
    @apellido           	varchar (200),
    @email               	varchar (500),
    @usuario_password    	varchar (500),
    @dni                 	integer
)
as
begin
    INSERT into usuarios (usuario, nombre, apellido, email, usuario_password, dni)
	VALUES (@nombreusuario, @nombre, @apellido, @email, @usuario_password, @dni)
end
go

---------------------------------------------------------------------------------------------- Registrar User
create or alter procedure updateUser
(
	@userID					integer,
    @nombreusuario      	varchar (255),
    @nombre             	varchar (200),
    @apellido           	varchar (200),
    @email               	varchar (500),
    @usuario_password    	varchar (500) = null,
    @dni                 	integer
)
as
begin
	IF @usuario_password IS NOT NULL AND LEN(@usuario_password) > 0
		UPDATE usuarios SET usuario = @nombreusuario, nombre=@nombre, apellido = @apellido, email = @email, 
			usuario_password= @usuario_password, dni = @dni
		WHERE id_usuario = @userID
	ELSE
		UPDATE usuarios SET usuario = @nombreusuario, nombre=@nombre, apellido = @apellido,
			email = @email, dni = @dni
		WHERE id_usuario = @userID
end
go
---------------------------------------------------------------------------------------------- Registrar User para Admin
create or alter procedure registerAdminUser
(
    @nombreusuario      	varchar (255),
    @nombre             	varchar (200),
    @apellido           	varchar (200),
    @email               	varchar (500),
    @usuario_password    	varchar (500),
    @dni                 	integer,
	@id_usuario				INTEGER		OUTPUT		
)
as
begin
    INSERT into usuarios (usuario, nombre, apellido, email, usuario_password, dni, isAdmin)
	VALUES (@nombreusuario, @nombre, @apellido, @email, @usuario_password, @dni, 1)

	SET @id_usuario = (SELECT MAX(id_usuario)
					  FROM usuarios
					  WHERE usuario = @nombreusuario);
	INSERT into administradores (id_usuario, intentos_fallidos, bloqueado)
	VALUES (@id_usuario, 0, 0)
end
go


-- SELECT * FROM usuarios us
-- JOIN administradores ad
-- ON us.id_usuario = ad.id_usuario
---------------------------------------------------------------------------------------------- Registrar Administrador
-- create or alter procedure registerAdminData
-- (
--     @nombreusuarioAdmin      	varchar (255),
--     @nombreAdmin             	varchar (200),
--     @apellidoAdmin           	varchar (200),
--     @emailAdmin               	varchar (500),
--     @usuario_passwordAdmin    	varchar (500),
--     @dniAdmin                 	integer
-- )
-- as
-- begin
-- 	Declare @tablevar table(id_usuario INTEGER)
-- 	insert into @tablevar(id_usuario) exec registerAdminUser @nombre = @nombreAdmin, @nombreusuario = @nombreusuarioAdmin, @apellido = @apellidoAdmin,
-- 		@email = @emailAdmin, @usuario_password = @usuario_passwordAdmin, @dni = @dniAdmin
-- 	insert into administradores values ((SELECT id_usuario FROM @tablevar), 0, 0, getdate())
-- end
-- go

declare @idUser smallint;
exec registerAdminUser @nombreusuario='administrator', @nombre='Gaston', @apellido='Ramirez', @email='gastonframirez@gmail.com', 
	@usuario_password='$2a$10$kHFGIEUvrU6DISWV4MgpmOD6XRAAVE2duQ8dQ5oCzeKt4.JLihfLu', @dni='38503389', @id_usuario=@idUser
go

---------------------------------------------------------------------------------------------- Obtener Lista de Comercios
create or alter procedure getAllComercios
as
begin
    SELECT * FROM comercios
end
go

-- execute getAllComercios


create or alter procedure getComercios
as
begin
    SELECT * FROM comercios
	WHERE habilitado = 1
end
go

-- execute getComercios

---------------------------------------------------------------------------------------------- Obtener Lista de Comercios con datos extr
create or alter procedure getComerciosExtra
(
	@idComercio integer = null
)
as
begin
	IF @idComercio IS NOT NULL AND LEN(@idComercio) > 0
		SELECT id_comercio, (SELECT COUNT(*) FROM ofertas offer
			WHERE offer.id_comercio = com.id_comercio AND offer.habilitada=1) as q_offers, nombre_publico, habilitado, 
			ISNULL((SELECT SUM(valor) FROM (SELECT count(*)*ctt.valor as valor FROM transacciones tr
				JOIN comisiones_tipo_transacciones ctt
					ON ctt.id_tipo = tr.id_tipo_transaccion
					AND ctt.id_comercio = tr.id_comercio
					AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
				WHERE tr.id_comercio=com.id_comercio
					AND Year(tr.fecha) = Year(CURRENT_TIMESTAMP) 
					AND Month(tr.fecha) = Month(CURRENT_TIMESTAMP)
					
				group by ctt.valor, tr.id_comercio) as valor),0)as tot_comm,
				logo_url,
				nombre_publico as nombre,
				com.direccion as serv_status
		FROM comercios com
		WHERE com.id_comercio = @idComercio
	ELSE
		SELECT id_comercio, (SELECT COUNT(*) FROM ofertas offer
				WHERE offer.id_comercio = com.id_comercio AND offer.habilitada=1) as q_offers, nombre_publico, habilitado, 
				ISNULL((SELECT SUM(valor) FROM (SELECT count(*)*ctt.valor as valor FROM transacciones tr
					JOIN comisiones_tipo_transacciones ctt
						ON ctt.id_tipo = tr.id_tipo_transaccion
						AND ctt.id_comercio = tr.id_comercio
						AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
					WHERE tr.id_comercio=com.id_comercio
						AND Year(tr.fecha) = Year(CURRENT_TIMESTAMP) 
						AND Month(tr.fecha) = Month(CURRENT_TIMESTAMP)
						
					group by ctt.valor, tr.id_comercio) as valor),0)as tot_comm,
					logo_url,
					nombre_publico as nombre,
					com.direccion as serv_status
			FROM comercios com
end
go

-- execute getComerciosExtra @idComercio = 1
-- go

---------------------------------------------------------------------------------------------- Obtener Datos de Comercio
create or alter procedure getDatosComercio
(
	@idComercio smallint
)
as
begin
    SELECT co.id_comercio, razon_social, cuit, direccion, nombre_publico, telefono, habilitado, 
		logo_url, cp, totalCrawl
	FROM comercios co
	WHERE co.id_comercio = @idComercio
end
go

-- execute getDatosComercio @idComercio=4
-- go
---------------------------------------------------------------------------------------------- Obtener Datos de Scraping Comercio

---------------------------------------------------------------------------------------------- Obtener Datos de Comercio: Precio Comision
create or alter procedure getValoresComisionesComercio
(
	@idComercio smallint
)
as
begin
    SELECT comi.nombre, fecha_inicio, fecha_fin, valor, co.id_comercio
	FROM comercios co
	RIGHT JOIN (SELECT nombre, fecha_inicio, fecha_fin, valor, id_comercio, tt.id_tipo FROM comisiones_tipo_transacciones ctt
				JOIN tipo_transacciones tt
					ON tt.id_tipo = ctt.id_tipo
				WHERE ctt.id_comercio = @idComercio
				AND ctt.fecha_inicio<CURRENT_TIMESTAMP
				AND (ISNULL(ctt.fecha_fin,CURRENT_TIMESTAMP+1) >CURRENT_TIMESTAMP)) comi
		ON comi.id_comercio = co.id_comercio
	WHERE co.id_comercio = @idComercio
end
go

-- EXECUTE getValoresComisionesComercio @idComercio=1
-- go
---------------------------------------------------------------------------------------------- Get Comercio ID
create or alter procedure getComercioID
(
	@CUIT varchar (12),
	@nombre	varchar(255)
)
AS
BEGIN
	SELECT id_comercio
	FROM comercios
	WHERE cuit = @CUIT
	AND nombre_publico = @nombre
end
GO

---------------------------------------------------------------------------------------------- Guardar Comercio
create or alter procedure saveComercio
(
	@razonSocial varchar (255),
	@CUIT varchar (12),
	@direccion varchar (255),
	@nombre varchar( 255),
	@telefono varchar (13),
	@logoURL varchar (500),
	@CP	integer, 
	@totalCrawl		integer
)
AS
begin
	INSERT INTO comercios (razon_social, cuit, direccion, nombre_publico, cp, telefono, logo_url, totalCrawl, habilitado)
	VALUES (@razonSocial, @CUIT, @direccion, @nombre, @CP, @telefono, @logoURL, @totalCrawl, 1)
end
go

---------------------------------------------------------------------------------------------- Guardar Comercio
create or alter procedure saveServicesComercio
(
	@idComercio integer,
	@idTecnologia integer,
	@baseURL varchar (500),
	@port integer,
	@funcion varchar (500),
	@tipoServicio varchar (100),
	@authToken		varchar(500)	
)
AS
begin
	declare @idTipo tinyint = (SELECT id_tipo_servicio from tipo_servicios WHERE nombre = @tipoServicio);

	INSERT INTO servicios (id_comercio, id_tipo_servicio, id_tecnologia, service_url, puerto, funcion, auth_token)
	VALUES (@idComercio, @idTipo, @idTecnologia, @baseURL, @port, @funcion, @authToken)
end
GO
---------------------------------------------------------------------------------------------- Guardar Comercio
create or alter procedure saveComisionesComercio
(
	@idComercio integer,
	@offerComm float,
	@productComm float
)
AS
begin
	INSERT INTO comisiones_tipo_transacciones
	VALUES (1, @idComercio, getdate(), null, @productComm);
	INSERT INTO comisiones_tipo_transacciones
	VALUES (2, @idComercio, getdate(), null, @offerComm)
end
go

---------------------------------------------------------------------------------------------- Guardar Comercio
create or alter procedure saveScraperURLComercio
(
	@idComercio integer,
	@idCategoria integer,
	@url	varchar (500)
)
AS
begin
	INSERT INTO scraper_categoria
	VALUES (@idComercio, @idCategoria, @url)
end
GO

---------------------------------------------------------------------------------------------- Editar Comercio
create or alter procedure saveScraperConfigComercio
(
	@idComercio integer,
	@cssClass varchar (255),
	@needsCrawl bit,
	@searchInName bit,
	@name varchar (255)
)
AS
begin
	INSERT INTO scraper_config
	VALUES (@idComercio, @name, @cssClass, @searchInName, @needsCrawl)
end
go
---------------------------------------------------------------------------------------------- Toggle Categoria
create or alter procedure toggleComercio

(
	@idComercio		integer,
	@habilitado			BIT
)
AS
BEGIN
	UPDATE comercios set habilitado = @habilitado
	WHERE id_comercio =@idComercio
end
go

---------------------------------------------------------------------------------------------- Editar Comercio
create or alter procedure updateComercio
(
	@idComercio integer,
	@razonSocial varchar (255),
	@CUIT varchar (12),
	@direccion varchar (255),
	@nombre varchar( 255),
	@telefono varchar (13),
	@logoURL varchar (500),
	@CP	integer,
	@totalCrawl		integer
)
AS
begin
	UPDATE comercios set razon_social = @razonSocial, cuit = @CUIT, direccion = @direccion, nombre_publico = @nombre,
		telefono = @telefono, logo_url=@logoURL, totalCrawl=@totalCrawl
	WHERE id_comercio = @idComercio
end
go

---------------------------------------------------------------------------------------------- Editar Comercio
create or alter procedure updateServicesComercio
(
	@idComercio integer,
	@idTecnologia integer,
	@baseURL varchar (500),
	@port integer,
	@funcion varchar (500),
	@tipoServicio varchar (100),
	@authToken		varchar(500)	
)
AS
begin
	declare @idTipo tinyint = (SELECT id_tipo_servicio from tipo_servicios WHERE nombre = @tipoServicio);

	UPDATE servicios SET id_tecnologia = @idTecnologia, service_url = @baseURL, puerto = @port,
		funcion = @funcion, auth_token = @authToken
	WHERE id_tipo_servicio = @idTipo
	AND id_comercio = @idComercio
end
GO

---------------------------------------------------------------------------------------------- Editar Comercio
create or alter procedure updateComisionesComercio
(
	@idComercio integer,
	@offerComm float,
	@productComm float
)
AS
begin
	IF ((SELECT valor FROM comisiones_tipo_transacciones WHERE id_tipo=1 AND id_comercio=@idComercio AND getdate() >= fecha_inicio and getdate()<ISNULL(fecha_fin, CURRENT_TIMESTAMP+1)) <> @productComm)
		INSERT into comisiones_tipo_transacciones values(1, @idComercio, getdate(), null, @productComm);
	IF ((SELECT valor FROM comisiones_tipo_transacciones WHERE id_tipo=2 AND id_comercio=@idComercio AND getdate() >= fecha_inicio and getdate()<ISNULL(fecha_fin, CURRENT_TIMESTAMP+1)) <> @offerComm)
		INSERT into comisiones_tipo_transacciones values(2, @idComercio, getdate(), null, @offerComm);
end
go

-- SELECT valor FROM comisiones_tipo_transacciones WHERE id_tipo=1 AND id_comercio=4 AND getdate() >= fecha_inicio and getdate()<ISNULL(fecha_fin, CURRENT_TIMESTAMP+1) 
-- go

-----TRIGGER
CREATE OR ALTER TRIGGER tu_ru_comisiones 
ON comisiones_tipo_transacciones
FOR insert
as
begin
	IF EXISTS (SELECT * FROM comisiones_tipo_transacciones ctt
				JOIN inserted i
				ON i.id_tipo = ctt.id_tipo
				AND ctt.id_comercio = i.id_comercio )
	UPDATE comisiones_tipo_transacciones
		SET fecha_fin=(SELECT fecha_inicio from Inserted i)
		WHERE id_comercio = (SELECT id_comercio from Inserted i)
		AND id_tipo = (SELECT id_tipo from inserted i)
		AND valor <> (SELECT valor from inserted i)
	
end
go
---------------------------------------------------------------------------------------------- Editar Comercio
create or alter procedure updateScraperURLComercio
(
	@idComercio integer,
	@idCategoria integer,
	@url	varchar (500)
)
AS
begin
	IF EXISTS (SELECT * FROM scraper_categoria WHERE id_categoria = @idCategoria AND id_comercio = @idComercio)
		UPDATE scraper_categoria 
			SET url_scrapping = @url
		WHERE id_categoria = @idCategoria
			AND id_comercio = @idComercio
	ELSE
		INSERT INTO scraper_categoria (id_categoria, id_comercio, url_scrapping) VALUES
		(@idCategoria, @idComercio, @url)
end
GO

---------------------------------------------------------------------------------------------- Editar Comercio
create or alter procedure updateScraperConfigComercio
(
	@idComercio integer,
	@cssClass varchar (255),
	@needsCrawl bit,
	@searchInName bit,
	@name varchar (255)
)
AS
begin
	IF EXISTS (SELECT * FROM scraper_config WHERE id_comercio = @idComercio
	AND prop_name = @name)
		UPDATE scraper_config
			SET class_name = @cssClass, needs_crawl = @needsCrawl,
			is_in_title = @searchInName
		WHERE id_comercio = @idComercio
		AND prop_name = @name
	ELSE
		exec saveScraperConfigComercio @idComercio=@idComercio, @cssClass = @cssClass, 
			@needsCrawl = @needsCrawl, @searchInName = @searchInName, @name = @name
END
go
---------------------------------------------------------------------------------------------- Obtener Lista de Categorias
create  or alter procedure getCategorias
(
	@idCategoria	integer = null
)
as
begin
	IF @idCategoria IS NOT NULL AND LEN(@idCategoria) > 0
		SELECT cp.id_categoria, ci.nombre, cp.habilitado, image_url, idi.nombre as lang FROM categorias_productos cp
			JOIN categorias_idiomas ci
				ON cp.id_categoria = ci.id_categoria
			JOIN idiomas idi
				ON ci.id_idioma = idi.id_idioma
			WHERE cp.habilitado = 1
			AND cp.id_categoria = @idCategoria
		ORDER BY ci.nombre
		
	ELSE
		SELECT cp.id_categoria, ci.nombre, cp.habilitado, image_url, idi.nombre as lang FROM categorias_productos cp
			JOIN categorias_idiomas ci
				ON cp.id_categoria = ci.id_categoria
			JOIN idiomas idi
				ON ci.id_idioma = idi.id_idioma
			WHERE cp.habilitado = 1
		ORDER BY ci.nombre
end
go
-- execute getCategorias
-- go
---------------------------------------------------------------------------------------------- Obtener Lista de Categorias
create or alter procedure getCategoriasAdmin
(
	@idCategoria integer = null
)
as
begin
	IF @idCategoria IS NOT NULL AND LEN(@idCategoria) > 0
		SELECT cp.id_categoria, ci.nombre, cp.habilitado, cp.image_url, idi.nombre as lang FROM categorias_productos cp
			JOIN categorias_idiomas ci
				ON cp.id_categoria = ci.id_categoria
			JOIN idiomas idi
				ON ci.id_idioma = idi.id_idioma
		WHERE cp.id_categoria = @idCategoria
		
	ELSE
		SELECT cp.id_categoria, ci.nombre, cp.habilitado, cp.image_url, idi.nombre as lang FROM categorias_productos cp
		JOIN categorias_idiomas ci
			ON cp.id_categoria = ci.id_categoria
		JOIN idiomas idi
			ON ci.id_idioma = idi.id_idioma    
end
go
-- execute getCategoriasAdmin @idCategoria=1
-- go

create or alter procedure getCategoriasLang
(
	@lang 	varchar(4)
)
as
begin
    SELECT cp.id_categoria, ci.nombre, cp.habilitado, idi.nombre as lang FROM categorias_productos cp
	JOIN categorias_idiomas ci
		ON cp.id_categoria = ci.id_categoria
	JOIN idiomas idi
		ON ci.id_idioma = idi.id_idioma
		AND idi.nombre=@lang
	WHERE cp.habilitado = 1
end
go

-- execute getCategoriasLang @lang='es'
-- go
---------------------------------------------------------------------------------------------- Checkear si categoria se puede habilitar

create or alter procedure checkHabilitable
(
	@idCategoria integer
)
AS
begin
	SELECT *
	FROM comercios com
	WHERE NOT EXISTS(
		SELECT * FROM scraper_categoria sc
		WHERE sc.id_comercio = com.id_comercio
		AND sc.id_categoria = @idCategoria		
	)
end
go

-- execute checkHabilitable @idCategoria=2
-- go

---------------------------------------------------------------------------------------------- Guardar Categoria
create or alter procedure saveCategoria
(
	@esp				varchar (255),
	@eng				varchar (255),
	@image_url			varchar (500)
)
AS
BEGIN
	INSERT into categorias_productos (nombre, habilitado,image_url)
	VALUES (@esp,1, @image_url);
	declare @idCategoria integer = (SELECT id_categoria
								FROM categorias_productos
								WHERE nombre = @esp);
	declare @idEsp integer = (SELECT id_idioma
								FROM idiomas
								WHERE nombre = 'es');
	declare @idEng integer = (SELECT id_idioma
								FROM idiomas
								WHERE nombre = 'en');
	INSERT INTO categorias_idiomas (id_categoria, id_idioma, nombre)
	VALUES (@idCategoria, @idEng, @eng),
	(@idCategoria, @idEsp, @esp)
end
go
---------------------------------------------------------------------------------------------- Guardar Traduccion Categoria
create  or alter procedure saveTranslationCategoria
(
	@idCategoria		smallint,
	@idIdioma          	smallint,
	@traduccion			varchar (255)
)
AS
BEGIN
	INSERT into categorias_idiomas
	VALUES (@idCategoria, @idIdioma, @traduccion)
end
go

---------------------------------------------------------------------------------------------- Obtener Categoria
create or alter procedure getCategoria
(
	@nombre				varchar (255)
)
AS
BEGIN
	SELECT * from categorias_productos cp
	JOIN categorias_idiomas ci
		ON cp.id_categoria = ci.id_categoria
	WHERE cp.nombre = @nombre
end
go

---------------------------------------------------------------------------------------------- Editar Categoria
create or alter procedure toggleCategoria

(
	@idCategoria		integer,
	@habilitado			BIT
)
AS
BEGIN
	UPDATE categorias_productos set habilitado = @habilitado
	WHERE id_categoria =@idCategoria
end
go

---------------------------------------------------------------------------------------------- Editar Categoria
create or alter procedure updateCategoria
(
	@idCategoria		smallint,
	@nombre          	varchar (255),
	@imgURL			varchar (500)
)
AS
BEGIN
	UPDATE categorias_productos set nombre = @nombre, image_url = @imgURL
	WHERE id_categoria = @idCategoria
end
go
---------------------------------------------------------------------------------------------- Editar Traduccion Categoria
create or alter procedure updateTranslationCategoria
(
	@idCategoria		smallint,
	@nameIdioma			varchar(3),
	@traduccion			varchar (255)
)
AS
BEGIN
	UPDATE categorias_idiomas set nombre = @traduccion
	WHERE id_categoria = @idCategoria AND id_idioma = (SELECT id_idioma from idiomas idi WHERE nombre = '@nameIdioma')
end
go
---------------------------------------------------------------------------------------------- Obtener Lista de Productos x categoria
create or alter procedure getProductos
(
	@categoria	smallint
)
as
begin
    SELECT prod.id_producto, nombre, modelo, precio, url_producto, fecha_actualizado, image_url, logo_url, prodc.id_comercio
	FROM productos prod
	JOIN producto_comercio prodc
		ON prodc.id_producto = prod.id_producto
	JOIN comercios co
		ON prodc.id_comercio = co.id_comercio
	WHERE co.habilitado = 1
	AND prodc.habilitado = 1
	AND prod.id_categoria = @categoria
	ORDER BY prod.id_producto, prodc.precio, prod.modelo

end
go
-- execute getProductos @categoria = 1
-- go
---------------------------------------------------------------------------------------------- Obtener Producto
create  or alter procedure getProducto
(
	@idProducto integer,
	@idComercio	integer
)
as
begin
	SELECT pc.modelo_producto, pc.nombre, image_url, precio, url_producto, 
			logo_url, co.nombre_publico FROM producto_comercio pc
	JOIN comercios co
		ON pc.id_comercio = co.id_comercio
	WHERE id_producto = @idProducto
		AND pc.id_comercio = @idComercio
end
go
-- exec getProducto @idProducto=2, @idComercio=4
-- go
---------------------------------------------------------------------------------------------- Guardar Producto
create or alter procedure saveProducto
(
	@nombre            	varchar(255),
    @marca            	varchar(255),
    @modelo            	varchar(255),
	@modeloNativo		varchar(255),
    @idCategoria      	smallint,
	@idComercio			SMALLINT,
	@precio				float,
	@urlProducto		varchar (500),
	@imageUrl           varchar (500)
)
as
BEGIN
-- Insertar producto en productos y luego obtener el id del producto e insertar el resto de la info en producto_comercio. Poner habilitado = 1 en ambas tablas
	IF NOT EXISTS (SELECT * FROM productos WHERE modelo = @modelo AND id_categoria = @idCategoria AND nombre_marca = @marca)
		INSERT INTO productos (nombre_marca, modelo, id_categoria) VALUES (@marca, @modelo, @idCategoria);

	declare @idProducto smallint;
	
	SET @idProducto = (SELECT id_producto from productos where modelo = @modelo AND nombre_marca = @marca);

	IF NOT EXISTS (SELECT * FROM producto_comercio WHERE id_producto = @idProducto AND id_comercio = @idComercio)
		INSERT INTO producto_comercio (id_producto, id_comercio, precio, url_producto, fecha_actualizado,
										image_url, modelo_producto, nombre, habilitado)
				VALUES (@idProducto, @idComercio, @precio, @urlProducto, getdate(), @imageUrl, @modeloNativo,
						@nombre, 1);
	ELSE
		UPDATE producto_comercio SET precio = @precio, fecha_actualizado = getdate(), image_url = @imageUrl,
									url_producto=@urlProducto, nombre = @nombre, habilitado = 1
				WHERE id_producto = @idProducto AND id_comercio = @idComercio;
END
go

---------------------------------------------------------------------------------------------- Obtener Ofertas
create or alter procedure getOfertas
as
begin
    SELECT id_oferta, ofe.image_url as oferta_img_url, fecha_fin, fecha_inicio, 
	url_oferta, ofe.id_comercio, logo_url as comercio_logo_url, id_oferta_comercio
	FROM ofertas ofe
	JOIN comercios com
		ON ofe.id_comercio = com.id_comercio
		AND com.habilitado = 1
	WHERE habilitada = 1
		
end
go

-- EXECUTE getOfertas
-- go
---------------------------------------------------------------------------------------------- Obtener Oferta
create or alter procedure getOferta
(
	@idOferta integer,
	@idComercio	integer
)
as
begin
    SELECT nombre_publico, logo_url, url_oferta from ofertas offers
	JOIN comercios co
		ON offers.id_comercio = co.id_comercio
	WHERE offers.id_comercio = @idComercio
		AND offers.id_oferta = @idOferta
		
end
go

-- EXECUTE getOferta @idOferta=2, @idComercio=1
-- go
---------------------------------------------------------------------------------------------- Save Ofertas
create or alter procedure saveOferta
(
	@idComercio 		integer,
	@imageURL			varchar(500),
	@fechaInicio		datetime,
	@fechaFin			datetime,
	@urlOferta			varchar(500),
	@idOfertaCom		varchar(200)
)
as
begin
  IF EXISTS (SELECT * from ofertas WHERE id_comercio = @idComercio AND id_oferta_comercio = @idOfertaCom)
	UPDATE ofertas set image_url = @imageURL, fecha_inicio = @fechaInicio, fecha_fin = @fechaFin,
						url_oferta = @urlOferta, actualizada = getdate(), habilitada = 1
		WHERE id_comercio = @idComercio AND id_oferta_comercio = @idOfertaCom
  ELSE
	INSERT INTO ofertas (id_comercio, image_url, fecha_inicio, fecha_fin, url_oferta, id_oferta_comercio, actualizada, habilitada)
	VALUES (@idComercio, @imageURL, @fechaInicio, @fechaFin, @urlOferta, @idOfertaCom, getdate(), 1);
end
go

---------------------------------------------------------------------------------------------- Disable Ofertas
create or alter procedure disableOfertas
(
	@idComercio 		integer = null
)
as
begin
	IF @idComercio IS NOT NULL AND LEN(@idComercio) > 0
		UPDATE ofertas SET habilitada = 0
		WHERE id_comercio = @idComercio
		AND DATEDIFF(minute,actualizada, getdate()) >= 60
	ELSE
		UPDATE ofertas SET habilitada = 0
		WHERE DATEDIFF(minute,actualizada, getdate()) >= 60
end
go

-- SELECT * from ofertas where dATEDIFF(minute,actualizada, getdate()) >= 60
-- go
-- execute disableOfertas
-- go

---------------------------------------------------------------------------------------------- Disable Productos
create or alter procedure disableProducts
(
	@idComercio 		integer = null
)
as
begin
	IF @idComercio IS NOT NULL AND LEN(@idComercio) > 0
		UPDATE producto_comercio SET habilitado = 0
		WHERE id_comercio = @idComercio
		AND DATEDIFF(hour,fecha_actualizado,getdate()) >= 12
	ELSE
		UPDATE producto_comercio SET habilitado = 0
		WHERE DATEDIFF(hour,fecha_actualizado,getdate()) >= 12
end
go
-- execute disableProducts
-- go

---------------------------------------------------------------------------------------------- Save Transaccion
create or alter procedure saveTransaccion
(
    @fechaTransaccion   	varchar(100),
    @idProducto      		integer = null,
    @idTipoTransaccion    	SMALLINT,
    @idComercio       		SMALLINT,
    @idUsuario         		INTEGER,
    @idOferta    			integer = null,
    @pending     			bit
)
as
BEGIN

    insert into transacciones (	fecha,
                                id_producto,
                                id_tipo_transaccion,
                                id_comercio,
                                id_usuario,
                                id_oferta,
                                pending)
    values (@fechaTransaccion,
            @idProducto,
            @idTipoTransaccion,
            @idComercio,
            @idUsuario,
            @idOferta,
            @pending
			)
    
END
go
---------------------------------------------------------------------------------------------- List Pending Transaccion
create or alter procedure listPendingTransaction
(
    @idComercio   		integer
)
as
BEGIN
	SELECT tr.id_transaccion, tr.fecha, tr.pending, pc.modelo_producto, pc.nombre as prod_name, pc.precio as precio_producto, 
			tt.nombre as nombre_transaction, ctt.valor as commission, offers.id_oferta_comercio,
			us.nombre as userName, us.apellido, us.email, us.dni FROM transacciones tr
	LEFT JOIN producto_comercio pc
		ON pc.id_producto = tr.id_producto
		AND tr.id_comercio = pc.id_comercio
	LEFT JOIN productos prod
		ON tr.id_producto = prod.id_producto
	JOIN tipo_transacciones tt
		ON tr.id_tipo_transaccion = tt.id_tipo
	JOIN comisiones_tipo_transacciones ctt
		ON tr.id_tipo_transaccion = ctt.id_tipo
		AND tr.id_comercio = ctt.id_comercio
		AND tr.fecha >= ctt.fecha_inicio AT TIME ZONE 'Argentina Standard Time' and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
	LEFT JOIN ofertas offers
		ON offers.id_oferta = tr.id_oferta
	JOIN usuarios us
		ON us.id_usuario = tr.id_usuario
	WHERE tr.id_comercio = @idComercio
	AND pending = 1
END
go

-- execute listPendingTransaction @idComercio=1
-- go
---------------------------------------------------------------------------------------------- Release Pending Transaccion
create or alter procedure releasePendingTransaction
(
    @idTransaccion   		integer
)
as
BEGIN

    UPDATE transacciones
	SET pending = 0
	WHERE id_transaccion = @idTransaccion
    
END
go
---------------------------------------------------------------------------------------------- Comercios Categorias Scraper
create or alter procedure getComerciosURLScraper
(
	@idComercio 	INTEGER,
	@habilitado		BIT = null
)
as
begin
	IF @habilitado IS NOT NULL AND LEN(@habilitado) > 0
		SELECT com.id_comercio, nombre_publico, cp.id_categoria, sc.url_scrapping
		FROM comercios com
		JOIN scraper_categoria sc
			ON	sc.id_comercio = com.id_comercio
		JOIN categorias_productos cp
			ON cp.id_categoria = sc.id_categoria
		WHERE com.id_comercio = @idComercio
			AND cp.habilitado = @habilitado
	ELSE
		SELECT com.id_comercio, nombre_publico, cp.id_categoria, sc.url_scrapping
		FROM comercios com
		JOIN scraper_categoria sc
			ON	sc.id_comercio = com.id_comercio
		JOIN categorias_productos cp
			ON cp.id_categoria = sc.id_categoria
		WHERE com.id_comercio = @idComercio
end
go

-- EXECUTE getComerciosURLScraper @idComercio=71, @habilitado=1
-- go
---------------------------------------------------------------------------------------------- Comercios CSS Scraper

create or alter procedure getComerciosCSSScraper
(
	@idComercio 	INTEGER
)
as
begin
    SELECT com.id_comercio, id_scrap_config, prop_name, class_name, is_in_title, needs_crawl
	FROM comercios com
	JOIN scraper_config sc
		ON com.id_comercio = sc.id_comercio
	WHERE com.id_comercio = @idComercio
end
go


-- EXECUTE getComerciosCSSScraper @idComercio = 4
-- go
-- insert into scraper_config values(71, 'prodURL', '.itemBox--price .value-item', 0, 0)
-- go

---------------------------------------------------------------------------------------------- Comercios CSS Scraper
create or alter procedure getTecnologias
as
begin
    SELECT *
	FROM tecnologias
end
go

-- execute getTecnologias
-- go
---------------------------------------------------------------------------------------------- Nuevos Users del mes
create or alter procedure newMonthlyUsers
(
	@date 	DATETIME
)
AS
BEGIN
	SELECT count(*) as stats
	FROM usuarios
	WHERE Year(fecha_registro) = Year(@date) 
    AND Month(fecha_registro) = Month(@date)
END
go
-- execute newMonthlyUsers @date='2019-01-20'


---------------------------------------------------------------------------------------------- Cant de transacciones mes
create or alter procedure monthlyTransactionsCount
(
	@date 	DATETIME,
	@comercioID	integer = null
)
AS
BEGIN
	IF @comercioID IS NOT NULL AND LEN(@comercioID) > 0
		SELECT count(*) as stats
			FROM transacciones
		WHERE Year(fecha) = Year(@date) 
    		AND Month(fecha) = Month(@date)
			AND id_comercio = @comercioID
	ELSE
		SELECT count(*) as stats
			FROM transacciones
		WHERE Year(fecha) = Year(@date) 
			AND Month(fecha) = Month(@date)
END
go

-- execute monthlyTransactionsCount @date='2019-02-20', @comercioID=1
-- go

----------------------------------------------------------------------------------------------  Transacciones del mes
create or alter procedure monthlyTransactionsList
(
	@date 	DATETIME,
	@comercioID	integer = null
)
AS
BEGIN
	IF @comercioID IS NOT NULL AND LEN(@comercioID) > 0
		SELECT id_transaccion, fecha, tr.id_oferta, offer.url_oferta, pending, tt.nombre as nombre_trans, ctt.valor, pc.nombre as nombre_prod, pc.precio
			FROM transacciones tr
		JOIN tipo_transacciones tt
			ON tt.id_tipo = tr.id_tipo_transaccion
		JOIN comisiones_tipo_transacciones ctt
			ON tr.id_tipo_transaccion = ctt.id_tipo
			AND tr.id_comercio = ctt.id_comercio
			AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
		LEFT JOIN producto_comercio pc
			ON pc.id_producto = tr.id_producto
			AND pc.id_comercio = tr.id_comercio
		LEFT JOIN productos prod
			ON tr.id_producto = prod.id_producto
		LEFT JOIN ofertas offer
			ON offer.id_oferta = tr.id_oferta
		WHERE Year(fecha) = Year(@date) 
    		AND Month(fecha) = Month(@date)
			AND tr.id_comercio = @comercioID
	ELSE
		SELECT id_transaccion, fecha, tr.id_oferta, offer.url_oferta, pending, tt.nombre as nombre_trans, ctt.valor, pc.nombre as nombre_prod, pc.precio
			FROM transacciones tr
		JOIN tipo_transacciones tt
			ON tt.id_tipo = tr.id_tipo_transaccion
		JOIN comisiones_tipo_transacciones ctt
			ON tr.id_tipo_transaccion = ctt.id_tipo
			AND tr.id_comercio = ctt.id_comercio
			AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
		LEFT JOIN producto_comercio pc
			ON pc.id_producto = tr.id_producto
			AND pc.id_comercio = tr.id_comercio
		LEFT JOIN productos prod
			ON tr.id_producto = prod.id_producto
		LEFT JOIN ofertas offer
			ON offer.id_oferta = tr.id_oferta
		WHERE Year(fecha) = Year(@date) 
			AND Month(fecha) = Month(@date)
END
go
-- execute monthlyTransactionsList @date='2019-02-10', @comercioID=1
-- go
----------------------------------------------------------------------------------------------  Transacciones historicas
create or alter procedure historicalTransactionsList
(
	@comercioID	integer = null
)
AS
BEGIN
	IF @comercioID IS NOT NULL AND LEN(@comercioID) > 0
		SELECT id_transaccion, fecha, tr.id_oferta, offer.url_oferta, pending, tt.nombre as nombre_trans, ctt.valor, pc.nombre as nombre_prod, pc.precio
			FROM transacciones tr
		JOIN tipo_transacciones tt
			ON tt.id_tipo = tr.id_tipo_transaccion
		JOIN comisiones_tipo_transacciones ctt
			ON tr.id_tipo_transaccion = ctt.id_tipo
			AND tr.id_comercio = ctt.id_comercio
			AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
		LEFT JOIN producto_comercio pc
			ON pc.id_producto = tr.id_producto
			AND pc.id_comercio = tr.id_comercio
		LEFT JOIN productos prod
			ON tr.id_producto = prod.id_producto
		LEFT JOIN ofertas offer
			ON offer.id_oferta = tr.id_oferta
		WHERE tr.id_comercio = @comercioID
	ELSE
		SELECT id_transaccion, fecha, tr.id_oferta, offer.url_oferta, pending, tt.nombre as nombre_trans, ctt.valor, pc.nombre as nombre_prod, pc.precio
			FROM transacciones tr
		JOIN tipo_transacciones tt
			ON tt.id_tipo = tr.id_tipo_transaccion
		JOIN comisiones_tipo_transacciones ctt
			ON tr.id_tipo_transaccion = ctt.id_tipo
			AND tr.id_comercio = ctt.id_comercio
			AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
		LEFT JOIN producto_comercio pc
			ON pc.id_producto = tr.id_producto
			AND pc.id_comercio = tr.id_comercio
		LEFT JOIN productos prod
			ON tr.id_producto = prod.id_producto
		LEFT JOIN ofertas offer
			ON offer.id_oferta = tr.id_oferta
END
go

-- execute historicalTransactionsList @comercioID=1
-- go
---------------------------------------------------------------------------------------------- Nuevos Users del mes
create or alter procedure activeOffers
(
	@idComercio	integer = null
)
AS
BEGIN
	IF @idComercio IS NOT NULL AND LEN(@idComercio) > 0
		SELECT count(*) as stats
		FROM ofertas
		WHERE habilitada = 1
		AND id_comercio = @idComercio
	ELSE
		SELECT count(*) as stats
		FROM ofertas
		WHERE habilitada = 1
END
go

--  EXECUTE activeOffers @idComercio = 1
-- go
---------------------------------------------------------------------------------------------- Nuevos Users del mes
create or alter procedure actionsByType
(
	@comercioID	integer = null
)
AS
BEGIN
	IF @comercioID IS NOT NULL AND LEN(@comercioID) > 0
		SELECT nombre, count(*) as stats
		FROM transacciones tr
		RIGHT JOIN tipo_transacciones tt
			ON tr.id_tipo_transaccion = tt.id_tipo
		WHERE id_comercio = @comercioID
		AND Year(fecha) = Year(CURRENT_TIMESTAMP) 
		AND Month(fecha) = Month(CURRENT_TIMESTAMP)
		GROUP BY nombre
	ELSE
		SELECT nombre, count(*) as stats
		FROM transacciones tr 
		RIGHT JOIN tipo_transacciones tt
			ON tr.id_tipo_transaccion = tt.id_tipo
		WHERE Year(fecha) = Year(CURRENT_TIMESTAMP) 
		AND Month(fecha) = Month(CURRENT_TIMESTAMP)
		GROUP BY nombre
END
go
-- execute actionsByType
-- execute actionsByType @comercioID=1
-- go
-- drop procedure getTypes
-- GO

create  or alter procedure getTypes
AS
BEGIN
	SELECT * FROM tipo_transacciones
end
go
-- exec getTypes
-- go
---------------------------------------------------------------------------------------------- Cantidad de productos mostrados

create or alter procedure cantProductosActivos
(
	@idComercio	integer = null
)
AS
BEGIN
	IF @idComercio IS NOT NULL AND LEN(@idComercio) > 0
		SELECT count(1) as stats FROM productos pr
		JOIN producto_comercio pc
			ON pr.id_producto = pc.id_producto
		WHERE pc.habilitado = 1
		AND pc.id_comercio = @idComercio
	ELSE 
		SELECT count(1) as stats FROM productos pr
		JOIN producto_comercio pc
			ON pr.id_producto = pc.id_producto
		WHERE pc.habilitado = 1
END
go

-- execute cantProductosActivos @idComercio = 71
-- go
---------------------------------------------------------------------------------------------- Transacciones en historial
create  or alter procedure getCantTransactionsSinceRegistry
(
	@comercioID	integer
)
AS
BEGIN
	SELECT count(*) as stats FROM transacciones
	WHERE id_comercio = @comercioID		
END
go
-- execute getCantTransactionsSinceRegistry @comercioID=71
-- go
---------------------------------------------------------------------------------------------- Usuarios activos del mes (hicieron trans)
-- drop procedure getCantActiveUsersInMonth
-- go

create  or alter procedure getCantActiveUsersInMonth
(
	@comercioID	integer
)
AS
BEGIN
	SELECT count(DISTINCT(id_usuario)) as stats FROM transacciones
	WHERE id_comercio = @comercioID
	AND Year(fecha) = Year(CURRENT_TIMESTAMP) 
	AND Month(fecha) = Month(CURRENT_TIMESTAMP)
END
go
-- execute getCantActiveUsersInMonth @comercioID=71
-- go
---------------------------------------------------------------------------------------------- Evolucion de $ por mes

	-- SELECT distinct YEAR(fecha) as year_transaction,
    --      MONTH(fecha) as mes_transaction,
    --      ISNULL(SUM(valor),0) AS total_mes,
	-- 	 id_tipo

			 
    -- FROM transacciones tr
	-- JOIN comisiones_tipo_transacciones ctt 
	-- 	ON  ctt.id_comercio=@comercioID 
	-- 	AND ctt.id_tipo=tr.id_tipo_transaccion
	
	-- GROUP BY YEAR(fecha), MONTH(fecha), id_tipo
	-- ORDER BY YEAR(fecha), MONTH(fecha)
create or alter procedure comissionsEvolution
(
	@comercioID	integer = null
)
AS
BEGIN
	select distinct YEAR(d.date)  year_transaction, MONTH(d.date) as month_transaction, isnull(t.amnt, 0) as month_total, isnull(id_tipo,0) as tipo from (
    SELECT
        YEAR(fecha) as 'Year', 
        MONTH(fecha) as 'Month', 
        sum(valor) as amnt,
        id_tipo
    FROM transacciones tr
	JOIN comisiones_tipo_transacciones ctt 
		ON  ctt.id_comercio=@comercioID 
		AND ctt.id_tipo=tr.id_tipo_transaccion
		AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
    where tr.id_comercio = @comercioID
    group by YEAR(fecha), Month(fecha), id_tipo
) t
right join (
    select dateadd(mm, -number, getdate()) as date
    from master.dbo.spt_values 
    where type = 'p' and number < 12
) d  on year(d.date) = t.[year] and month(d.date) = t.[month]
order by YEAR(d.date), MONTH(d.date)
END
go

-- execute comissionsEvolution @comercioID=1
-- go
---------------------------------------------------------------------------------------------- Obtener Comision Total
create or alter procedure getComisiones
(
	@mes	datetime,
	@comercioID	smallint,
	@idTipo smallint = null
)
as
begin	
	IF @idTipo IS NOT NULL AND LEN(@idTipo) > 0
				SELECT distinct SUM(valor) AS stats
				FROM transacciones tr
				JOIN comisiones_tipo_transacciones ctt 
					ON  ctt.id_comercio=@comercioID 
					AND ctt.id_tipo=tr.id_tipo_transaccion
					AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
				WHERE id_tipo = @idTipo
				AND MONTH(fecha) = MONTH(@mes)
				AND YEAR(fecha) = YEAR(@mes)
				
		ELSE
				SELECT distinct SUM(valor) AS stats, id_tipo
				FROM transacciones tr
				JOIN comisiones_tipo_transacciones ctt 
					ON  ctt.id_comercio=@comercioID 
					AND ctt.id_tipo=tr.id_tipo_transaccion
					AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
				WHERE MONTH(fecha) = MONTH(@mes)
				AND YEAR(fecha) = YEAR(@mes)
				GROUP BY id_tipo
end
go

-- declare @dt as datetime = datetimefromparts(2019,2,1,17,0,0,0)

-- execute getComisiones @mes=@dt, @comercioID=71
-- go

---------------------------------------------------------------------------------------------- Obtener Comision Total
create or alter procedure getComisionesTotal
(
	@mes	datetime,
	@comercioID	smallint
)
as
begin	
	SELECT distinct SUM(valor) AS stats
		FROM transacciones tr
		JOIN comisiones_tipo_transacciones ctt 
			ON  ctt.id_comercio=@comercioID 
			AND ctt.id_tipo=tr.id_tipo_transaccion
			AND tr.fecha >= ctt.fecha_inicio and tr.fecha<ISNULL(ctt.fecha_fin, CURRENT_TIMESTAMP) 
		WHERE MONTH(fecha) = MONTH(@mes)
		AND YEAR(fecha) = YEAR(@mes)
		AND tr.id_comercio = @comercioID
end
go

-- declare @dt as datetime = datetimefromparts(2019,2,1,17,0,0,0)

-- execute getComisionesTotal @mes=@dt, @comercioID=4
-- go
---------------------------------------------------------------------------------------------- Obtener servicios
drop procedure getServices
GO

create or alter procedure getServices
(
	@idComercio		integer
)
AS
BEGIN
	SELECT id_servicio, id_comercio, tech.id_tecnologia, tech.javaClass, 
	tech.nombre as tech_name, auth_token, puerto, funcion, service_url,
	ts.nombre as tipo_nombre FROM servicios serv
	JOIN tipo_servicios ts
		ON ts.id_tipo_servicio = serv.id_tipo_servicio
	JOIN tecnologias tech
		ON serv.id_tecnologia = tech.id_tecnologia
	WHERE id_comercio = @idComercio
END
GO

-- execute getServices @idComercio=4
-- go
---------------------------------------------------------------------------------------------- Obtener tipos servicios
create or alter procedure getServicesTypes
AS
BEGIN
	SELECT * FROM tipo_servicios
END
go

-- exec getServicesTypes

---------------------------------------------------------------------------------------------- Guardar configuraciones
create or alter procedure saveOrchConfig
(
	@tipoConfig		integer,
	@word			varchar(500)
)
AS
begin
	IF NOT EXISTS (SELECT * FROM orchestrator_config WHERE palabra_ignorar=@word and tipo_config=@tipoConfig)
		INSERT INTO orchestrator_config values (@tipoConfig, @word, 1, getdate())
	ELSE
		UPDATE orchestrator_config set habilitado = 1, actualizado = getdate() WHERE palabra_ignorar=@word and tipo_config=@tipoConfig
end
go

---------------------------------------------------------------------------------------------- Deshabilitar configuraciones
create or alter procedure disableOrchConfig
AS
begin
	UPDATE orchestrator_config set habilitado = 0
	WHERE DATEDIFF(minute,actualizado, getdate()) >= 1
end
go

---------------------------------------------------------------------------------------------- Obtener configuraciones
create or alter procedure getOrchConfig
AS
begin
	SELECT * FROM orchestrator_config WHERE habilitado = 1
end
go

---------------------------------------------------------------------------------------------- Obtener productos de busqueda
create or alter procedure searchProd
(
	@searchStr varchar(500)
)
AS
begin
	SELECT prod.id_producto, nombre, modelo, precio, url_producto, fecha_actualizado, image_url, logo_url, prodc.id_comercio
	FROM productos prod
	JOIN producto_comercio prodc
		ON prodc.id_producto = prod.id_producto
	JOIN comercios co
		ON prodc.id_comercio = co.id_comercio
	WHERE co.habilitado = 1
	AND prodc.habilitado = 1
	AND prodc.nombre COLLATE Latin1_general_CI_AI  LIKE CONCAT('%', @searchStr, '%') COLLATE Latin1_general_CI_AI
	ORDER BY prod.id_producto, prodc.precio, prod.modelo
end
go

-- exec searchProd @searchStr='pava%marfil'
-- go



