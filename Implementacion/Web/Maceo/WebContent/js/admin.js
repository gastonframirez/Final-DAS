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
              $("body").removeClass("loaderActive");
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
	                      $("body").removeClass("loaderActive");
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
		$("body").addClass("loaderActive");
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
	              $("body").removeClass("loaderActive");
	              showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(error1) + " " + $.i18n.prop(enable) + 
	            		  " " + $.i18n.prop(error2) , '', 'error');
	          },
	          success: function(res) {        	 
	        	  console.log(res);
	        	  $("body").removeClass("loaderActive");
	        	  showSwal('error', 'success', $.i18n.prop('success'),  $.i18n.prop(success1) + " " + $.i18n.prop(enable) + 
	            		  " " + $.i18n.prop(success2), '', 'success');
	          }
	      });
	});
	
	$.validator.addMethod('cssClass', function(value) {

        return value.match(/^([.#]([\w.-]+))$/);
	
	});
	$.validator.addMethod('urlLocal', function(value) {
        return value.match(/^https?:\/\/\w+(\.\w+)*(:[0-9]+)?\/?(\/[.\w]*)*(\?)*(\w+)*(:[0-9]+)*$/);
	});
	
	$("#cssModelInTitle").on("change", function(){
		if($(this).is(':checked')) {
		    $("#cssModel").prop('disabled', true);
		    $("#cssModelNeedsCrawl").prop('disabled', true);
		    
		} else {
			$("#cssModel").prop('disabled', false);
			$("#cssModelNeedsCrawl").prop('disabled', false);
		}
	})
	
	$("#cssBrandInTitle").on("change", function(){
		if($(this).is(':checked')) {
		    $("#cssBrand").prop('disabled', true);
		    $("#cssBrandNeedsCrawl").prop('disabled', true);
		    
		} else {
			$("#cssBrand").prop('disabled', false);
			$("#cssBrandNeedsCrawl").prop('disabled', false);
		}
	})
	$("#addComercioForm").validate({
		 rules: {
		    publicName:"required",
			companyName:"required",
			CUIT:{
				required:true,
				number: true,
				minlength:11,
				maxlength:11
			},
			address:"required",
			zipCode:{
				required:true,
				number:true
			},
			phone:{
				required: true,
				number: true
			},
			logoURL:{
				required:true,
				url:true
			},
			authToken:"required",
			baseURL:{
				required:true,
				urlLocal:true
			},
			port:{
				required:true,
				number:true
			},
			"#baseURLtransacciones":{
				required:true,
				urlLocal:true
			},
			ppProd:{
				required:true,
				number:true
			},
			ppOffer:{
				required:true,
				number:true
			},
			urlCategorias:{
				url:true
			},
			cssIterator:{
				required:true,
			},
			cssIterator:{
				required:true,
			},
			cssNombre:{
				required:true,
			},
			cssModel:{
				required:true,
			},
			cssBrand:{
				required:true,
			},
			cssPrice:{
				required:true,
			},
			cssImgURL:{
				required:true,
			},
			cssProductURL:{
				required:true,
			},
			paginationNextv:{
				required:true,
			},
			function:"required",
			paginationParam:"required"
	      },
	      messages: {
	    	  publicName: $.i18n.prop('publicNameVal'),
				companyName: $.i18n.prop('companyName'),
				CUIT:{
					required: $.i18n.prop('CUITVal'),
					number: $.i18n.prop('CUITLenVal'),
					minlength: $.i18n.prop('CUITLenVal'),
					maxlength: $.i18n.prop('CUITLenVal')
				},
				address: $.i18n.prop('addressVal'),
				zipCode: $.i18n.prop('zipCodeVal'),
				port: $.i18n.prop('portVal'),
				function: $.i18n.prop('functionVal'),
				phone: $.i18n.prop('phoneVal'),
				logoURL:{
					required: $.i18n.prop('logoURLVal'),
					url: $.i18n.prop('logoFormVal')
				},
				authToken: $.i18n.prop('authTokenVal'),
				baseURL:{
					required: $.i18n.prop('baseURLVal'),
					urlLocal: $.i18n.prop('baseURLFormVal')
				},
				ppProd: $.i18n.prop('ppProdVal'),
				ppOffer: $.i18n.prop('ppOfferVal'),
				urlCategorias:{
					required: $.i18n.prop('urlCategoriasVal'),
					url: $.i18n.prop('urlCategoriasFormVal')
				},
				cssIterator:{
					required: $.i18n.prop('cssIteratorVal'),
				},
				cssNombre:{
					required: $.i18n.prop('cssNombreVal'),
				},
				cssModel:{
					required: $.i18n.prop('cssModelVal'),
				},
				cssBrand:{
					required: $.i18n.prop('cssBrandVal'),
				},
				cssPrice:{
					required: $.i18n.prop('cssPriceVal'),
				},
				cssImgURL:{
					required: $.i18n.prop('cssImgURLVal'),
				},
				cssProductURL:{
					required: $.i18n.prop('cssProductURLVal'),
				},
				paginationNextv:{
					required: $.i18n.prop('paginationNextvVal'),
				},
				paginationParam: $.i18n.prop('paginationParamVal')
	      },
	      errorPlacement: function(label, element) {
	        label.addClass('mt-2 text-danger');
	        label.insertAfter(element);
	      },
	      highlight: function(element, errorClass) {
	        $(element).parent().addClass('has-danger')
	        $(element).addClass('form-control-danger')
	      }
	 });
	 $("#addComercioForm").on("submit", function(e){
		e.preventDefault();
		var isvalid = $("#addComercioForm").valid();
        if (isvalid) {
			var data = $( "#addComercioForm" ).serialize();
			var actionSuccessStr = "";
			var actionErrorStr = "";
			
			$("body").addClass("loaderActive");
			
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
//				              jUtils.showing("error", hr.error);
				        	  console.log(hr);
			                  console.log(JSON.parse(hr.responseText));
			                  var error = JSON.parse(hr.responseText);
			                  if(error.error!=undefined){
			                	  $("body").removeClass("loaderActive");
			                	  showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(error.error), '/Maceo', 'error');
			                  }else{
			                	  $("body").removeClass("loaderActive");
			                	  showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '/Maceo', 'error');
			                  }
				          },
				          success: function(res) {        	 
				        	  console.log(res);
				        	  $("body").removeClass("loaderActive");
				        	  showSwal('agregado-correcto', 'success', $.i18n.prop('success'), $.i18n.prop(actionSuccessStr), '/Maceo/admin/Home.do', 'success');
				          }
				      });
				}else{
					$("body").removeClass("loaderActive");
			  		showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop("errorConnection"), '', 'error');
				}
			});
        }
		return false;
	 })
	 
	 
	 
	 $("#addCategoriaForm").validate({
	      rules: {
	    	spanish: "required",
	    	english: "required",
	    	imageURL: {
	        	required: true,
		        url: true
		    },
	      },
	      messages: {
	    	spanish:  $.i18n.prop('spanishVal'),
			english:  $.i18n.prop('englishVal'),
			imageURL: {
				required:  $.i18n.prop('imageVal'),
				url: $.i18n.prop('imageURLVal'),
			},
	      },
	      errorPlacement: function(label, element) {
	        label.addClass('mt-2 text-danger');
	        label.insertAfter(element);
	      },
	      highlight: function(element, errorClass) {
	        $(element).parent().addClass('has-danger')
	        $(element).addClass('form-control-danger')
	      }
	 });
	 
	 $("#addCategoriaForm").on("submit", function(e){
		 
		e.preventDefault();
		var isvalid = $("#addCategoriaForm").valid();
        if (isvalid) {
			var data = $( "#addCategoriaForm" ).serialize();
			var actionSuccessStr = "";
			var actionErrorStr = "";
			$("body").addClass("loaderActive");
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
	              $("body").removeClass("loaderActive");
	              showSwal('agregado-correcto', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '/Maceo/admin/Home.do', 'error');
	          },
	          success: function(res) {        	 
	        	  console.log(res);
	        	  $("body").removeClass("loaderActive");
	        	  showSwal('agregado-correcto', 'success', $.i18n.prop('success'), $.i18n.prop(actionSuccessStr), '/Maceo/admin/Home.do', 'success');
	          }
	      });
        }
		return false;
	 })
	 $("#addGlobalConfig").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#addGlobalConfig" ).serialize();
		var actionSuccessStr = "successfullyEditConfig";
		var actionErrorStr = "errorEditConfig";
		$("body").addClass("loaderActive");
		$.ajax({
          url: "/Maceo/admin/AddConfiguracion.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
              $("body").addClass("removeActive");
              showSwal('agregado-correcto', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '/Maceo/admin/Home.do', 'error');
          },
          success: function(res) {        	 
        	  console.log(res);
        	  $("body").addClass("removeActive");
        	  showSwal('agregado-correcto', 'success', $.i18n.prop('success'), $.i18n.prop(actionSuccessStr), '/Maceo/admin/Home.do', 'success');
          }
      });
		
		return false;
	 })
	 
	  $(".btn-testConn").on("click", function(e){
		e.preventDefault();
		var actionSuccessStr = "successfulConnection";
		var actionErrorStr = "errorConnection";
		$("body").addClass("loaderActive");
		$.when(testConnection()).then(function(res){
			console.log(res);
			if(res==1){
				$("body").removeClass("loaderActive");
		       showSwal('error', 'success', $.i18n.prop('success'), $.i18n.prop(actionSuccessStr), '', 'success');
			 }else{
				 $("body").removeClass("loaderActive");
				 showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(actionErrorStr), '', 'error');
			 }
		});
		
		return false;
	 })
});
