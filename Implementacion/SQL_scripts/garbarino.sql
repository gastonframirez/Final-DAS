use garbarino
go
------------------ categorias_productos
INSERT into categorias_productos (nombre, image_url) VALUES ('Televisores', '');
INSERT into categorias_productos (nombre, image_url) VALUES ('Heladeras', '');
go

------------------ marcas
INSERT into marcas (nombre, image_url) VALUES ('Samsung', '');
INSERT into marcas (nombre, image_url) VALUES ('LG', '');
go
------------------ productos
INSERT INTO productos (nombre, id_categoria, id_marca, modelo, image_url, precio)
    VALUES ('Smart TV LG 32 " HD 32LK615BPSB', 1, 2, '32LK615BPSB', '', '12999.00');
INSERT INTO productos (nombre, id_categoria, id_marca, modelo, image_url, precio)
    VALUES ('Smart TV Samsung 50 " 4K Ultra HD UN50MU6100GCDF', 1, 1, 'UN50MU6100GCDF', '', '32999.00');

------------------ ofertas
INSERT into ofertas (fecha_inicio, fecha_fin, precio_oferta, id_producto, habilitada)
    VALUES ('2019-01-19', '2019-01-31', '25999.99', 4, 1);