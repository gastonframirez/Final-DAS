$(document).ready(function() {

	 
	 $("#addComercioForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#addComercioForm" ).serialize();
		var actionSuccessStr = "";
		var actionErrorStr = "";
		
		if($("#addComercioForm").hasClass("editForm")){
			actionSuccessStr = "successfullyEditStore";
			actionErrorStr = "errorEditStore"
		}else{
			actionSuccessStr = "successfullyAddingStore";
			actionErrorStr = "errorAddingStore"
		}
		
		$.ajax({
          url: "/Maceo/admin/AddComercio.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
              showSwal('agregado-correcto', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '/Maceo/admin/Home.do', 'error');
          },
          success: function(res) {        	 
        	  console.log(res);
        	  showSwal('agregado-correcto', 'success', $.i18n.prop('success'), $.i18n.prop(actionSuccessStr), '/Maceo/admin/Home.do', 'success');
          }
      });
		
		return false;
	 })
	 
	 $("#addCategoriaForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#addCategoriaForm" ).serialize();
		var actionSuccessStr = "";
		var actionErrorStr = "";
		
		if($("#addCategoriaForm").hasClass("editForm")){
			actionSuccessStr = "successfullyEditCategory";
			actionErrorStr = "errorEditCategory"
		}else{
			actionSuccessStr = "successfullyAddingCategory";
			actionErrorStr = "errorAddingCategory"
		}
		$.ajax({
          url: "/Maceo/admin/AddCategoria.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
              showSwal('agregado-correcto', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '/Maceo/admin/Home.do', 'error');
          },
          success: function(res) {        	 
        	  console.log(res);
        	  showSwal('agregado-correcto', 'success', $.i18n.prop('success'), $.i18n.prop(actionSuccessStr), '/Maceo/admin/Home.do', 'success');
          }
      });
		
		return false;
	 })
});