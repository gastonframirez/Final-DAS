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
              jUtils.hiding("resultRegistro");
              jUtils.showing("error", hr.responseText);
          },
          success: function(html) {
              jUtils.hiding("error");
              jUtils.showing("resultRegistro", html);
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
          error: function(hr){
              jUtils.showing("error", hr.error);
          },
          success: function(res) {        	 
        	  if(res.redirect){
        		  window.location.replace(res.redirect);
        	  }else{
                  jUtils.showing("error", res.error);
        	  }
          }
      });
		
		return false;
	 })
});