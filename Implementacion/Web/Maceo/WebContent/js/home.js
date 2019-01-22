var home = {
		
	cerrarSesion : function() {
		window.location = "/admin/Logout.do";
	},
	
	openNav : function() {
		$( "#mySidenav" ).css( "width", "250px" );
		$( "#main" ).css( "marginLeft", "250px" );
	},
	
	closeNav : function() {
		$( "#mySidenav" ).css( "width", "0" );
		$( "#main" ).css( "marginLeft", "0" );
	},
	
	show : function( element ) {
		$( "#administrador,#notas,#novedades,#concesionarias,#sorteos,#clientes" ).hide();
		$( "#" + element ).show();
		
		if( element != "concesionarias" && element != "sorteos" && element != "clientes" ) {
			$( "#backgroundImage" ).attr( "src", "/img/" + element + ".png" );
		}
		else {
			$( "#backgroundImage" ).removeAttr( "src" );
		}
	},
	
	showPopup : function() {
		window.location = "#popup";
	},
	
	showPopupCliente : function() {
		window.location = "#popupCliente";
	},
	
	solicitarAdhesion : function() {
		window.location = "/conc/Adhesion.do";
	},
	
	setPopupData : function (idConcesionaria,
							 razonSocial,
							 cuit,
							 provincia,
							 localidad,
							 direccion,
							 telefono,
							 estadoAutorizacion,
							 idServicio,
							 url,
							 funcion,
							 puerto,
							 tecnologia) {

		$( "#popup .popup h2#tituloPopup" ).html( razonSocial );

		var selectorContenido = $( "#popup .popup div#contenidoPopup" );
		
		selectorContenido.html( "" );

		if( estadoAutorizacion == 'p' ) {
		selectorContenido.append( "<div class=\"valorDatoEstado\">" + $.i18n.prop('pendiente') + "</div>" );
		}

		if( estadoAutorizacion == 'a' ) {
			selectorContenido.append( "<div class=\"valorDatoEstado\">" + $.i18n.prop('autorizada') + "</div>" );
		}

		if( estadoAutorizacion == 'd' ) {
			selectorContenido.append( "<div class=\"valorDatoEstado\">" + $.i18n.prop('denegada') + "</div>" );
		}
		
		selectorContenido.append( "<br /><hr /><br />" );
		
		selectorContenido.append( $.i18n.prop('cuit') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + cuit + "</span> <br /> <br />" );
		selectorContenido.append( $.i18n.prop('provincia') + "&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + provincia + "</span> <br /> <br />" );
		selectorContenido.append( $.i18n.prop('localidad') + "&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + localidad + "</span> <br /> <br />" );
		selectorContenido.append( $.i18n.prop('direccion') + "&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + direccion + "</span> <br /> <br />" );
		selectorContenido.append( $.i18n.prop('telefono') + "&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + telefono + "</span> <br />" );

		if( estadoAutorizacion == 'p' ) {
			
			concesionarias.getTecnologias();

			var codigoInline = "<form id=\"formularioServicio\" method=\"post\">";
			
			codigoInline += "<br /><div class=\"datosServicioPendientes\">";
			
			codigoInline += "<input type=\"hidden\" name=\"idConcesionaria\" value=\"" + idConcesionaria + "\" />";
			codigoInline += "<input maxlength=\"500\" name=\"url\" id=\"url\" type=\"url\" placeholder=\"" + $.i18n.prop('urlServ') + "\" required /> <br /> <br />";
			codigoInline += "<input maxlength=\"500\" name=\"funcion\" id=\"funcion\" type=\"url\" placeholder=\"" + $.i18n.prop('funcServ') + "\" required /> <br /> <br />";
			codigoInline += "<input maxlength=\"4\" name=\"puerto\" id=\"puerto\" type=\"number\" placeholder=\"" + $.i18n.prop('puertoServ') + "\" required /> <br /> <br />";
			codigoInline += "<div id=\"tecnologias\"></div>";
			codigoInline += "</div><br /><div class=\"popupButtons\">";
			codigoInline += "<input onclick=\"javascript:administradores.autorizar();\" class=\"popupButton\" type=\"button\" value=\"" + $.i18n.prop('autorizar') + "\" />";
			codigoInline += "<input onclick=\"javascript:administradores.denegar();\" class=\"popupButton\" type=\"button\" value=\"" + $.i18n.prop('denegar') + "\" />";
			
			codigoInline += "</div>";
			
			codigoInline += "</form>";
			
			selectorContenido.append( codigoInline );
		}
		
		if( estadoAutorizacion == 'a' ) {
			
			var codigoInline = "<br /><div class=\"datosServicioConfigurados\">";

			codigoInline += $.i18n.prop('urlServ') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + url + "</span> <br /> <br />";
			codigoInline += $.i18n.prop('funcServ') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + funcion + "</span> <br /> <br />";
			codigoInline += $.i18n.prop('puertoServ') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + puerto + "</span> <br /> <br />";
			codigoInline += $.i18n.prop('tecServ') + "&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + tecnologia + "</span> <br />";
			codigoInline += "</div><br /><div class=\"popupButtons\">";
			
			codigoInline += "<input onclick=\"javascript:administradores.revocar(" + idConcesionaria + ");\" class=\"popupButton\" type=\"button\" value=\"" + $.i18n.prop('revocar') + "\" />";
			codigoInline += "<input onclick=\"javascript:administradores.testearServicio(" + idServicio + ",'" + url + "','" + funcion + "'," + puerto + ",'" + tecnologia + "');\" class=\"popupButton\" type=\"button\" value=\"" + $.i18n.prop('testear') + "\" />";
			codigoInline += "<div id=\"testService\"></div>";
			
			codigoInline += "</div>";
			codigoInline += "</div>";
			
			selectorContenido.append( codigoInline );
		}
	},
	
	setPopupClienteData : function (nombre,dni,mail,provincia,direccion,cantCuotasPagas,alDia,aptoSorteo) {

		$( "#popupCliente .popupCliente h2#tituloPopupCliente" ).html( nombre );

		var selectorContenido = $( "#popupCliente .popupCliente div#contenidoPopupCliente" );
		
		selectorContenido.html( "" );

		selectorContenido.append( "<br /><hr /><br />" );
		
		selectorContenido.append( $.i18n.prop('dni') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + dni + "</span> <br /> <br />" );
		selectorContenido.append( $.i18n.prop('mail') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + mail + "</span> <br /> <br />" );
		selectorContenido.append( $.i18n.prop('provincia') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + provincia + "</span> <br /> <br />" );
		selectorContenido.append( $.i18n.prop('direccion') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + direccion + "</span> <br /> <br />" );
		selectorContenido.append( $.i18n.prop('ccp') + "&nbsp;&nbsp<span class=\"valorDato\">" + cantCuotasPagas + " / 60</span> <br /><br />" );
		selectorContenido.append( $.i18n.prop('alDia') + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + (alDia=="s"?$.i18n.prop('si') : $.i18n.prop('no')) + "</span> <br /><br />" );
		selectorContenido.append( $.i18n.prop('aptoSorteo') + "&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"valorDato\">" + (aptoSorteo=="s"?$.i18n.prop('apto') : $.i18n.prop('noApto')) + "</span> <br />" );
		},
	
	setearLimitesSorteo : function( ultimaFechaSorteo ) {
		
		var fechaApta = new Date(ultimaFechaSorteo);
		
		fechaApta.setMonth( fechaApta.getMonth() + 1 );
		
		$( "#fechaSorteo" ).attr( "min", home.formatDate(fechaApta) );
	},
	
	formatDate : function (date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
	}
}