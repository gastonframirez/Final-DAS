$(document).ready(function() {
	
	
	
//	$( "#userRegister" ).validate({
//		  rules: {
//		    password1: "required",
//		    password2: {
//		      equalTo: "#password1"
//		    }
//		  }
//		});
	$("#userRegister").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#userRegister" ).serialize();
		
		$.ajax({
          url: "/Maceo/users/Registrar.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
              showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop('errorRegisty'), '/Maceo', 'error');
          },
          success: function(res) {        	 
        	  console.log(res);
        	  showSwal('redirect', 'success', $.i18n.prop('success'), $.i18n.prop('successfulRegistry'), '/Maceo', 'success');
          }
      });
		
		return false;
	 })
	 
	 $("#loginForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#loginForm" ).serialize();
		
		$.ajax({
          url: "/Maceo/users/ValidateLogin.do",
          type: "post",
          data: data,
          dataType: "json",
//          error: function(hr){
//              jUtils.showing("error", hr.error);
//          },
//          success: function(res) {        	 
//        	  if(res.redirect){
//        		  window.location.replace(res.redirect);
//        	  }else{
//                  jUtils.showing("error", res.error);
//        	  }
//          }
          error: function(hr){
              jUtils.showing("error", hr.error);
              showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop('errorLogin'), '', 'error');
          },
          success: function(res) {        	 
        	  window.location.href="/Maceo";
          }
          
      });
		
		return false;
	 })
	 
	 $("#updateUserForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#updateUserForm" ).serialize();
		
		$.ajax({
		  url: "/Maceo/users/Registrar.do",
          type: "post",
          data: data,
          dataType: "html",
//          error: function(hr){
//              jUtils.showing("error", hr.error);
////              showSwal('agregado-correcto', 'danger', 'tituloo', 'textooo', './Home.do');
//          },
//          success: function(res) {        	 
//        	  console.log(res);
//        	  showSwal('agregado-correcto', 'success', 'Agregado con exito', 'Blah', './Home.do', 'success');
//          }
          error: function(hr){
              jUtils.showing("error", hr.error);
              showSwal('agregado-correcto', 'danger', $.i18n.prop('error'), $.i18n.prop('errorEditProfile'), '/Maceo', 'error');
          },
          success: function(res) {        	 
        	  console.log(res);
        	  showSwal('agregado-correcto', 'success', $.i18n.prop('success'), $.i18n.prop('successfullyEditProfile'), '/Maceo', 'success');
          }
      });
		
		return false;
	 })
	 $("#updateUserNormalForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#updateUserForm" ).serialize();
		
		$.ajax({
		  url: "/Maceo/users/Registrar.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
              showSwal('agregado-correcto', 'danger', $.i18n.prop('error'), $.i18n.prop('errorEditProfile'), '/Maceo', 'error');
          },
          success: function(res) {        	 
        	  console.log(res);
        	  showSwal('agregado-correcto', 'success', $.i18n.prop('success'), $.i18n.prop('successfullyEditProfile'), '/Maceo', 'success');
          }
      });
		
		return false;
	 })
});