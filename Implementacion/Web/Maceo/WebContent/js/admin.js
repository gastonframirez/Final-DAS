$(document).ready(function() {

	function testConnection(){
		var dfd = $.Deferred();
		
		var data =  {
				  "name":"tecnologia","tecnologia":$("#techID").val(), 
				  "authToken": $("#authToken").val(),
				  "funcion":$("#functionofertas").val(),
				  "url":$("#baseURLofertas").val()
			  };

		$.ajax({
          url: "/Maceo/admin/TestConnection.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
              showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '', 'error');
          },
          success: function(res) {
        	  var response = JSON.parse(res);
	        	  if(response.status==1){
	        		  var data =  {
	        				  "name":"tecnologia","tecnologia":$("#techID").val(), 
	        				  "authToken": $("#authToken").val(),
	        				  "funcion":$("#functiontransacciones").val(),
	        				  "url":$("#baseURLtransacciones").val()
	        			  };
	
	        		$.ajax({
	                  url: "/Maceo/admin/TestConnection.do",
	                  type: "post",
	                  data: data,
	                  dataType: "html",
	                  error: function(hr){
	                      jUtils.showing("error", hr.error);
	                      showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '', 'error');
	                  },
	                  success: function(res) {
	                	  var response2 = JSON.parse(res);
	                	  if(response2.status==1){
	                		  console.log(1);
	                		  dfd.resolve(1);
	                	  }else{
	                		  console.log(0);
	                		  dfd.resolve(0);
	                	  }
	                  }
	              });
        	  }else{
        		  console.log(0);
        		  dfd.resolve(0);
        	  }
          }
      });
	  return dfd.promise();
	}
	
	$("#enabled").on("change", function(e){
		
		var enable = "";
		var url = "";
		var data = "";
		var error1="";
		var error2="";
		var success1="";
		var success2="";
		if($(this).hasClass("comercioSwitch")){
			url="/Maceo/admin/ToggleComercio.do";
			data = $("#addComercioForm").serialize();
			error1="errorTogglingStore1"
			error2="errorTogglingStore2"
			success1="successTogglingStore1"
			success2="successTogglingStore2"
		}else{
			url="/Maceo/admin/ToggleCategoria.do"
		    data=$("#addCategoriaForm").serialize();
			error1="errorTogglingCat1"
		    error2="errorTogglingCat2"
		    success1="successTogglingCat1"
			success2="successTogglingCat2"
		}
		
		if($(this).is(":checked")){
			enable = "enabled";
		}else{
			enable = "disabled";
		}
		
		$.ajax({
	          url: url,
	          type: "post",
	          data: data,
	          dataType: "html",
	          error: function(hr){
	              jUtils.showing("error", hr.error);
	              showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(error1) + " " + $.i18n.prop(enable) + 
	            		  " " + $.i18n.prop(error2) , '', 'error');
	          },
	          success: function(res) {        	 
	        	  console.log(res);
	        	  showSwal('error', 'success', $.i18n.prop('success'),  $.i18n.prop(success1) + " " + $.i18n.prop(enable) + 
	            		  " " + $.i18n.prop(success2), '', 'success');
	          }
	      });
	});
	
	
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
		$.when(testConnection()).then(function(res){
			if(res==1){
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
			}else{
		  		  showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop("errorConnection"), '', 'error');
			}
		});
		
		return false;
	 })
	 
	 $("#addCategoriaForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#addCategoriaForm" ).serialize();
		var actionSuccessStr = "";
		var actionErrorStr = "";
		
		console.log($("#addCategoriaForm").hasClass("editForm"));
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
	 $("#addGlobalConfig").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#addGlobalConfig" ).serialize();
		var actionSuccessStr = "successfullyEditConfig";
		var actionErrorStr = "errorEditConfig";
		
		$.ajax({
          url: "/Maceo/admin/AddConfiguracion.do",
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
	 
	  $(".btn-testConn").on("click", function(e){
		e.preventDefault();
		var actionSuccessStr = "successfulConnection";
		var actionErrorStr = "errorConnection";
		$.when(testConnection()).then(function(res){
			console.log(res);
			if(res==1){
		       showSwal('error', 'success', $.i18n.prop('success'), $.i18n.prop(actionSuccessStr), '', 'success');
			 }else{
				 showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '', 'error');
			 }
		});
		
		return false;
	 })
});
