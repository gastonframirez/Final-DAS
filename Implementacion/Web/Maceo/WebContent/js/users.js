$(document).ready(function() {
	$("#userRegister").validate({
	      rules: {
	        nombre: "required",
	        apellido: "required",
	        dni: {
	        	required: true,
	        	number: true,
	        	minlength:7,
	        	maxlength:8
	        },
	        email: {
	        	required: true,
		        email: true
		    },
	        username: {
	          required: true,
	          minlength: 2
	        },
	        password1: {
	          required: true,
	          minlength: 5
	        },
	        password2: {
	          required: true,
	          minlength: 5,
	          equalTo: "#password1"
	        }
	      },
	      messages: {
			nombre:  $.i18n.prop('firstnameVal'),
			apellido:  $.i18n.prop('lastnameVal'),
			dni: {
				required:  $.i18n.prop('dniVal'),
				number: true,
				minlength: $.i18n.prop('dniValMinLength'),
				maxlength: $.i18n.prop('dniValMinLength'),
			},
			email: $.i18n.prop('emailVal'),
			username: {
				required:  $.i18n.prop('usernameVal'),
				minlength:  $.i18n.prop('usernameValLength'),
			},
			password1: {
				required:  $.i18n.prop('passwordVal'),
				minlength:  $.i18n.prop('passwordValLength'),
			},
			password2: {
				required:  $.i18n.prop('passwordConfVal'),
				minlength:  $.i18n.prop('passwordConfValLength'),
				equalTo:  $.i18n.prop('passwordConfValEqual')
			}
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
	
	$("#userRegister").on("submit", function(e){
		
		var isvalid = $("#userRegister").valid();
        if (isvalid) {
        	e.preventDefault();
        	
        	console.log("form is valid");
        	
    		var data = $( "#userRegister" ).serialize();
    		
    		$.ajax({
              url: "/Maceo/users/Registrar.do",
              type: "post",
              data: data,
              dataType: "html",
              error: function(hr){
                  console.log(hr);
                  console.log(JSON.parse(hr.responseText));
                  var error = JSON.parse(hr.responseText);
                  if(error.error!=undefined){
                	  showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop(error.error), '/Maceo', 'error');
                  }else{
                	  showSwal('error', 'danger', $.i18n.prop('error'), $.i18n.prop('errorRegisty'), '/Maceo', 'error');
                  }
                  
              },
              success: function(res) {        	 
            	  console.log(res);
            	  showSwal('redirect', 'success', $.i18n.prop('success'), $.i18n.prop('successfulRegistry'), '/Maceo', 'success');
              }
          });
        }
		
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
        	  console.log(hr.responseJSON.error);
              jUtils.showing("error", hr.responseJSON.error);
              showSwal('error', 'danger', $.i18n.prop('error'), hr.responseJSON.error, '', 'error');
          },
          success: function(res) {        	 
        	  window.location.href="/Maceo";
          }
          
      });
		
		return false;
	 })
	 
	
	 $("#updateUserForm").validate({
	      rules: {
	        nombre: "required",
	        apellido: "required",
	        dni: {
	        	required: true,
	        	number: true,
	        	minlength:7,
	        	maxlength:8
	        },
	        email: {
	        	required: true,
		        email: true
		    },
	        username: {
	          required: true,
	          minlength: 2
	        },
	        passwordVer: "required",
	        password1: {
	          minlength: 5
	        },
	        password2: {
	          minlength: 5,
	          equalTo: "#password1"
	        }
	      },
	      messages: {
			nombre:  $.i18n.prop('firstnameVal'),
			apellido:  $.i18n.prop('lastnameVal'),
			dni: {
				required:  $.i18n.prop('dniVal'),
				number: true,
				minlength: $.i18n.prop('dniValMinLength'),
				maxlength: $.i18n.prop('dniValMinLength'),
			},
			email: $.i18n.prop('emailVal'),
			username: {
				required:  $.i18n.prop('usernameVal'),
				minlength:  $.i18n.prop('usernameValLength'),
			},
			passwordVer: $.i18n.prop('passwordVal'),
			password1: {
				minlength:  $.i18n.prop('passwordValLength'),
			},
			password2: {
				minlength:  $.i18n.prop('passwordConfValLength'),
				equalTo:  $.i18n.prop('passwordConfValEqual')
			}
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
	 
	 $("#updateUserForm").on("submit", function(e){
		e.preventDefault();
		var isvalid = $("#updateUserForm").valid();
        if (isvalid) {
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
        }
		return false;
	 });
	 
	 
	 $("#updateUserNormalForm").validate({
	      rules: {
	        nombre: "required",
	        apellido: "required",
	        dni: {
	        	required: true,
	        	number: true,
	        	minlength:7,
	        	maxlength:8
	        },
	        email: {
	        	required: true,
		        email: true
		    },
	        username: {
	          required: true,
	          minlength: 2
	        },
	        passwordVer: "required",
	        password1: {
	          minlength: 5
	        },
	        password2: {
	          minlength: 5,
	          equalTo: "#password1"
	        }
	      },
	      messages: {
			nombre:  $.i18n.prop('firstnameVal'),
			apellido:  $.i18n.prop('lastnameVal'),
			dni: {
				required:  $.i18n.prop('dniVal'),
				number: true,
				minlength: $.i18n.prop('dniValMinLength'),
				maxlength: $.i18n.prop('dniValMinLength'),
			},
			email: $.i18n.prop('emailVal'),
			username: {
				required:  $.i18n.prop('usernameVal'),
				minlength:  $.i18n.prop('usernameValLength'),
			},
			passwordVer: $.i18n.prop('passwordVal'),
			password1: {
				minlength:  $.i18n.prop('passwordValLength'),
			},
			password2: {
				minlength:  $.i18n.prop('passwordConfValLength'),
				equalTo:  $.i18n.prop('passwordConfValEqual')
			}
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
	 
	 $("#updateUserNormalForm").on("submit", function(e){
		e.preventDefault();
		var isvalid = $("#updateUserForm").valid();
        if (isvalid) {
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
        }
		return false;
	 })
});