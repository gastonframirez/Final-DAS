var users = {
//		
//	setIdioma : function(idioma) {
//
//        jUtils.executing( "mensaje" );
//        $.ajax({
//            url: "/admin/SetearIdioma.do",
//            type: "post",
//            data: "idioma=" + idioma,
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("resultIdioma");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("resultIdioma", html);
//            }
//        });
//	},
//	
//	validarAdmin : function() {
//		
//        jUtils.executing( "mensaje" );
//        $.ajax({
//            url: "/admin/ValidarAdmin.do",
//            type: "post",
//            data: $( "#formulario" ).serialize(),
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("mensaje");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("mensaje", html);
//            }
//        });
//	},
	
	
	getProductos : function( nombreCategoria, idCategoria ) {
		console.log("getProductos");
		 var values = {
		            'nombre': nombreCategoria,
		            'idCategoria': idCategoria
		    };
        $.ajax({
            url: "/Maceo/productos/Productos.do",
            type: "post",
            data: values,
            error: function(hr){
                jUtils.hiding("productos");
                jUtils.showing("error", hr.responseText);
            },
            success: function(html) {
                jUtils.hiding("error");
                jUtils.hiding("home_clientes");
                jUtils.showing("productos", html);
            }
        });
	},
	
//	getNotas : function( info ) {
//		
//		console.log("getNotas");
//        jUtils.executing( "notas" );
//        $.ajax({
//            url: "/admin/Notas.do",
//            type: "post",
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("notas");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("notas", html);
//                
//                if( info ) {
//                	
//                    administradores.getConcesionarias( true );
//                }
//            }
//        });
//	},
//	
//	getConcesionarias : function( info ) {
//		
//		console.log("getConcesionarias");
//		jUtils.executing( "concesionarias" );
//		$.ajax({
//			url: "/admin/Concesionarias.do",
//			type: "post",
//			dataType: "html",
//            error: function(hr){
//                jUtils.hiding("concesionarias");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("concesionarias", html);
//            }
//		});
//	},
//	
//	autorizar : function() {
//		
//	    var bad = 0;
//	    $("#formularioServicio input").each(function ()
//	    {
//	        if ($.trim(this.value) == "") bad++;
//	    });
//	    if (bad > 0) {
//	    	alert( $.i18n.prop('completaCampos') );
//	    	return;
//	    }
//	    
//	    if( $( "#formularioServicio select").val() == null ) {
//	    	alert( $.i18n.prop('completaCampos') );
//	    	return;
//	    }
//		
//		var datos = $( "#formularioServicio" ).serialize();
//		
//        jUtils.executing( "resultadoAutorizacion" );
//        $.ajax({
//            url: "/admin/Autorizar.do",
//            type: "post",
//            data: datos,
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("resultadoAutorizacion");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("resultadoAutorizacion", html);
//            }
//        });
//	},
//	
//	denegar : function() {
//		
//		var idConcesionaria = $("#formularioServicio input[name='idConcesionaria']").val();
//		
//        jUtils.executing( "resultadoDenegacion" );
//        $.ajax({
//            url: "/admin/Denegar.do",
//            type: "post",
//            data: "idConcesionaria=" + idConcesionaria,
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("resultadoDenegacion");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("resultadoDenegacion", html);
//            }
//        });
//	},
//	
//	revocar : function(idConcesionaria) {
//		
//		if( !confirm($.i18n.prop('seguroRevocar') ) ) {
//			
//			return;
//		}
//		
//        jUtils.executing( "resultadoDenegacion" );
//        $.ajax({
//            url: "/admin/Denegar.do",
//            type: "post",
//            data: "idConcesionaria=" + idConcesionaria,
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("resultadoDenegacion");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("resultadoDenegacion", html);
//            }
//        });
//	},
//	
//	getSorteos : function() {
//		console.log("getSorteos");
//		jUtils.executing( "sorteos" );
//		$.ajax({
//			url: "/admin/Sorteos.do",
//			type: "post",
//			dataType: "html",
//            error: function(hr){
//                jUtils.hiding("sorteos");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("sorteos", html);
//            }
//		});
//	},
//	
//	cargarSorteo : function() {
//		
//		var day = new Date( $( "#fechaSorteo" ).val() );
//		
//		if( $("#fechaSorteo" ).val() == '' ) {
//			
//			alert( $.i18n.prop('dselFecha') );
//			return;
//		}
//		
//	    var isWeekend = (day.getDay() == 5) || ( day.getDay() == 6); 
//	    
//	    if( isWeekend ) {
//	    	
//	    	alert( $.i18n.prop('dselHabil') );
//	    	return;
//	    }
//	    
//	    var fechaMinima = new Date( $( "#fechaSorteo" ).attr( "min" ) );
//	    
//	    if( day < fechaMinima ) {
//	    	
//	    	alert( $.i18n.prop('fdsSupA') + ' ' + $( "#fechaSorteo" ).attr( "min" ) );
//	    	return;
//	    }
//	    
//		var datos = $( "#formularioSorteo" ).serialize();
//		
//        jUtils.executing( "resultadoCargaSorteo" );
//        $.ajax({
//            url: "/admin/CargarSorteo.do",
//            type: "post",
//            data: datos,
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("resultadoCargaSorteo");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("resultadoCargaSorteo", html);
//            }
//        });
//	},
//	
//	eliminarSorteo : function(idSorteo) {
//		
//		if( !confirm($.i18n.prop('segEliminarSorteo') ) ) {
//			
//			return;
//		}
//		
//        jUtils.executing( "resultadoCargaSorteo" );
//        $.ajax({
//            url: "/admin/EliminarSorteo.do",
//            type: "post",
//            data: "idSorteo=" + idSorteo,
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("resultadoCargaSorteo");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("resultadoCargaSorteo", html);
//            }
//        });
//	},
//	
//	getClientesFiltrado : function() {
//
//		console.log("getClientes");
//		jUtils.executing( "clientes" );
//		$.ajax({
//			url: "/admin/Clientes.do",
//			type: "post",
//			dataType: "html",
//			data: $("#filtroClientes").serialize(),
//            error: function(hr){
//                jUtils.hiding("clientes");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("clientes", html);
//            }
//		});
//	},
//	
//	getClientes : function() {
//		
//		console.log("getClientes");
//		jUtils.executing( "clientes" );
//		$.ajax({
//			url: "/admin/Clientes.do",
//			type: "post",
//			dataType: "html",
//			data: {concesionaria:"all",provincia:"all",apto:"all"},
//            error: function(hr){
//                jUtils.hiding("clientes");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("clientes", html);
//            }
//		});
//	},
//	
//	testearServicio : function(idServicio, url, funcion, puerto, tecnologia) {
//
//		console.log("testearServicio");
//		jUtils.executing( "testService" );
//		$.ajax({
//			url: "/admin/TestearServicio.do",
//			type: "post",
//			dataType: "html",
//			data: "idServicio=" + idServicio + "&url=" + url + "&funcion=" + funcion + "&puerto=" + puerto + "&tecnologia=" + tecnologia,
//            error: function(hr){
//                jUtils.showing("testService", "<br />" + hr.responseText);
//                jUtils.hiding("error");
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("testService", html);
//            }
//		});
//	},
//	
//	getProvinciasParaFiltro : function() {
//
//		console.log("getProvinciasParaFiltro");
//        jUtils.executing( "selectProvinciasClientes" );
//        $.ajax({
//            url: "/conc/Provincias.do",
//            type: "post",
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("selectProvinciasClientes");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("selectProvinciasClientes", html);
//            }
//        });
//	},
//	
//	getCuotas : function(idCo,idCl) {
//		console.log("getCuotas");
//        jUtils.executing( "resultadoCuotas" );
//        $.ajax({
//            url: "/admin/Cuotas.do",
//            type: "post",
//            dataType: "html",
//            data: "idConcesionaria=" + idCo + "&idCliente=" + idCl,
//            error: function(hr){
//                jUtils.hiding("resultadoCuotas");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("resultadoCuotas", html);
//            }
//        });
//	}
//};
//
//var concesionarias = {
//
//	getInformacion : function() {
//
//		concesionarias.getProvincias( true );
//	},
//	
//	getProvincias : function( info ) {
//
//		console.log("getProvincias");
//        jUtils.executing( "provincias" );
//        $.ajax({
//            url: "/conc/Provincias.do",
//            type: "post",
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("provincias");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("provincias", html);
//            }
//        });
//	},
//
//	getTecnologias : function() {
//
//		console.log("getTecnologias");
//        jUtils.executing( "tecnologias" );
//        $.ajax({
//            url: "/conc/Tecnologias.do",
//            type: "post",
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("tecnologias");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("tecnologias", html);
//            }
//        });
//	},
//	
//	getLocalidades : function(obj) {
//
//		console.log("getLocalidades");
//        jUtils.executing( "localidades" );
//        $.ajax({
//            url: "/conc/Localidades.do",
//            type: "post",
//            data: {"idProvincia":obj.value},
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("localidades");
//                jUtils.showing("error", hr.responseText);
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("localidades", html);
//            }
//        });
//	},
//	
//	solicitar : function() {
//		
//		var datos = $( "#formularioAdhesion" ).serialize();
//        jUtils.executing( "carga" );
//        $.ajax({
//            url: "/conc/Solicitar.do",
//            type: "post",
//            data: datos,
//            dataType: "html",
//            error: function(hr){
//                jUtils.hiding("carga");
//                jUtils.showing("error", hr.responseText);
//                $( "#error" ).append( "<br /><button formaction=\"/conc/Adhesion.do\">Volver</button>" );
//            },
//            success: function(html) {
//                jUtils.hiding("error");
//                jUtils.showing("carga", html);
//            }
//        });
//        jUtils.hiding("solicitarDiv");
//	}
};