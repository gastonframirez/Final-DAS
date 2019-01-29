$(document).ready(function() {

	 
	 $("#addComercioForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#addComercioForm" ).serialize();
		
		$.ajax({
          url: "/Maceo/admin/AddComercio.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
//              showSwal('agregado-correcto', 'danger', 'tituloo', 'textooo', './Home.do');
          },
          success: function(res) {        	 
        	  console.log(res);
        	  showSwal('agregado-correcto', 'success', 'Agregado con exito', 'Blah', './Home.do', 'success');
          }
      });
		
		return false;
	 })
	 
	 $("#addCategoriaForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#addCategoriaForm" ).serialize();
		
		$.ajax({
          url: "/Maceo/admin/AddCategoria.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
//              showSwal('agregado-correcto', 'danger', 'tituloo', 'textooo', './Home.do');
          },
          success: function(res) {        	 
        	  console.log(res);
        	  showSwal('agregado-correcto', 'success', 'Agregado con exito', 'Blah', './Home.do', 'success');
          }
      });
		
		return false;
	 })
});