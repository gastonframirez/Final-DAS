# Final Desarrollo Avanzado de Software
Repositorio con el código y documentación del programa para el examen final de Diseño Avanzado de Software - Universidad Blas Pascal - 22 de Febrero 2019


## Enunciado
Sin duda alguna, cada vez son más las personas que utilizan Internet para realizar sus compras, y muchas veces se encuentran con el inconveniente de que existen tantas ofertas para el mismo producto con diferentes precios, que terminan sin saber si adquirieron su producto al mejor precio.

Por tal, se solicita desarrollar un portal de comparación para la compra de electrodomésticos entre las páginas de comercios como Fravega, Garbarino, Musimundo, etc. para que el usuario pueda buscar un producto por categoría y rápidamente sepa en cuál página encuentra el precio más conveniente.

Cuando el usuario decida ingresar a la página del producto, Ud. deberá, previamente, solicitarle que se autentique en su portal para luego redirigirlo procurando generar una transacción con el sistema del comercio al que pertenece la página, indicándole los datos del usuario y la oferta de su interés. Después de esto, Ud. perderá todo control sobre la operación. 
En el caso de que el usuario no se haya registrado en el portal, Ud. deberá solicitarle un correo, apellido, nombre y una clave de acceso para posteriormente completar el direccionamiento.

También, se solicita desarrollar un dashboard desde el cual se permita consultar las transacciones realizadas, y otras estadísticas que considere que sean importantes, a fin de llevar un control de la comisión que deberá cobrarle al comercio.

Alguna de estos comercios podrán solicitarle publicar ofertas en su portal, por lo que Ud. deberá contemplar un home donde mostrar las mismas. Estos banners tendrán una comisión distinta al que obtiene a partir de las búsquedas.

Para poder construir esta solución Ud. deberá estudiar frameworks que le permitan realizar scraping y crawling de una página Web. Para llevar adelante este rastreo, Ud. podrá utilizar la tecnología que le resulte más conveniente.

> NOTA: El inicio de la transacción con el comercio es un paso ficticio que Ud. deberá cumplimentar como requerimiento de la solución solicitada.

## Pautas
El siguiente documento tiene como finalidad dejar claramente especificados las condiciones con las cuales el alumno puede presentarse a rendir el examen final.

### Respecto al software
Del caso de estudio “Mi asistente de compras de electrodomésticos online”, se requiere desarrollar obligatoriamente las siguientes funcionalidades:
-	Logueo y deslogueo de los usuarios administradores del sitio
-	Home con información de las ofertas de comercios y acceso a la búsqueda de productos
-	Configuración de los comercios adheridos
-	Rastreo automático de las páginas de los comercios adheridos
-	Registro de nuevos usuarios del portal
-	Logueo y deslogueo del usuario del portal
-	Búsqueda de productos 
-	Selección de un producto, autenticación y redireccionamiento a la página del comercio con los datos del producto seleccionado
-	Obtención de ofertas a publicar en el home
-	Estadísticas sobre el uso del portal

El software deberá implementarse usando el framework MVC3.2 desarrollado durante el cursado de la materia. No se permitirá el uso de scriptlets y menos utilizar la librería SQL de JSTL para procesar los datos. Se requiere que la aplicación sea internacionalizada en español e inglés y desarrollar una arquitectura orientada a servicios para toda la solución del portal. La elección de la tecnología se deja a criterio del estudiante.

El motor de base de datos a utilizar será SQL Server 2012 o superior.
Se deberá rastrear al menos 3 páginas de comercios de venta de electrodomésticos y 5 categorías de electrodomésticos. Cada uno deberá contar con su propio servicio construido en JAX-WS, Axis y REST, respectivamente.
Las opciones propias de los servicios se deberán implementar utilizando el patrón DAO propio del framework. El comportamiento con Javascript deberá implementarse utilizando el framework jQuery. 

### Respecto a la documentación
Se solicita entregar los siguientes documentos:
1. Análisis de requisitos
2. Análisis SOA
3. Modelo de datos 
4. Maquetas

### Antes del examen final
El alumno deberá presentarse 2 semanas antes del examen final para revisar el software.

### Durante el examen final
Se realizará un cruce de información entre la documentación entregada y el software desarrollado para determinar la coherencia entre ambos. A su vez, se realizarán preguntas teóricas sobre la arquitectura utilizada.
Posteriormente, una vez finalizada la revisión del software, se asignará una tarea de modificación sobre el programa o la definición de un caso a resolver en el momento que puedo o no haber sido contemplado en el caso de estudio y puedo o no corresponderse con el proyecto.


# CDN
Los logos de los comercios se van a encontrar en https://github.com/gastonframirez/cdnDAS

# Milestones
[February 1st](https://github.com/gastonframirez/Final-DAS/milestone/1)

[February 8th](https://github.com/gastonframirez/Final-DAS/milestone/2)

***

# Estadisticas elegidas:
## Generales:
- [x] Total de transacciones
- [x] Ofertas activas
- [x] Cantidad de usuarios nuevos en el mes
- [x] Grafico comparativo entre transacciones de productos y ofertas

## De cada comercio:
- [x] Tabla de todas las transacciones en el sitio (filtrable)
### Banners
- [ ] Total de comision del mes
- [x] Cantidad de productos mostrados
- [x] Cantidad de ofertas activas
- [x] Total de transacciones

### Graficos
- [x] Grafico comparativo entre transacciones de productos y ofertas
- [ ] Evolucion de $ en el mes (dividido por tipo)
- [ ] Registro de caidas del servicio
