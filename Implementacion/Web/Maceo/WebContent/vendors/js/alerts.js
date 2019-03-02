$(function($) {
	
   changeToRegister = function(title, textToShow, url){
	   Swal.fire({
 		  title: title,
 		  type: 'warning',
 		  html:	textToShow +
	    		    '<form id="registroPopUp"><input id="nombre" name="nombre" class="swal2-input" placeholder="'+$.i18n.prop('formNombre')+'" required>'+
	    		    '<input id="apellido" name="apellido" class="swal2-input" placeholder="'+$.i18n.prop('formApellido')+'" required>' +
	    		    '<input id="dni" name="dni" class="swal2-input" placeholder="'+$.i18n.prop('formDNI')+'" required>' +
	    		    '<input id="username" name="username" class="swal2-input" placeholder="'+$.i18n.prop('formUser')+'" required>' +
	    		    '<input id="email" name="email" class="swal2-input" placeholder="'+$.i18n.prop('formEmail')+'" required>' +
	    		    '<input id="password1" type="password" name="password1" class="swal2-input" placeholder="'+$.i18n.prop('formPass')+'" required>'+
	    		    '<input id="password2" type="password" name="password2" class="swal2-input" placeholder="'+$.i18n.prop('formPassVer')+'" required></form>',
	    	  showCancelButton: true,
 		  confirmButtonText: $.i18n.prop('register'),
 		  cancelButtonText: $.i18n.prop('cancel'),
 		  confirmButtonColor: '#28a745',
 		  showLoaderOnConfirm: true,
 		  allowOutsideClick: false,
			  allowEscapeKey: false,
 		  preConfirm: () => {
 			  var data = $("#registroPopUp").serialize();
 			  return fetch('/Maceo/users/Registrar.do', {
 				    method: 'post',
 				    headers: {
 				      "Content-type": "application/x-www-form-urlencoded; charset=UTF-8",
 				      Accept: 'application/json'
 				    },
 				    body: data
 				  })
 				  .then(function(response) {
						        if (!response.ok) {
						        	throw response.json();
						        }
						        return response;
						    })
	    		      .then(response => {
	    		    	return response.json();
	    		      }).catch(error => {
	    		    	  console.log(error);
	    		    	  error.then(function(err){
	    		    		   Swal.showValidationMessage(
 		    		          `Error: ${err.error}`
 		    		        )
	    		    	  })
	    		      })
 			 
 		  },
 		}).then((result) => {
 			  if (result.value) {
 				  //Todo bien entonces --> A pagina de redireccion
 				    Swal.fire({
 				      title:  $.i18n.prop('redirecting')
 				    }).then(function(){
 				    	window.location.href = url;
 				    });
 				  }
 				})
   }	

   showSwal = function(type, result, title, textToShow, url, icon) {
	  'use strict';
	    if (type === 'error') {
	    	Swal.fire({
			  title: title,
			  text: textToShow,
			  type: icon,
			  allowOutsideClick: false,
			  allowEscapeKey: false,
			  confirmButtonClass: "btn btn-"+result
	    	})

	    } else if (type === 'loginNeeded') {
	    	Swal.fire({
	    		  title: title,
	    		  type: 'warning',
	    		  html:	textToShow +
		    		    '<form id="login"><input id="username" name="username" class="swal2-input" placeholder="'+$.i18n.prop('formUser')+'" required>' +
		    		    '<input id="password" type="password" name="password" class="swal2-input" placeholder="'+$.i18n.prop('formPass')+'" required></form>',
				  footer: '<a href="javascript:changeToRegister(\''+$.i18n.prop('register')+'\',\''+$.i18n.prop('needToReg')+'\', \''+url+'\')">'+$.i18n.prop('noAccount')+'</a>',

	    		  showCancelButton: true,
	    		  confirmButtonText: $.i18n.prop('login'),
	    		  cancelButtonText: $.i18n.prop('cancel'),
	    		  confirmButtonColor: '#28a745',
	    		  showLoaderOnConfirm: true,
	    		  allowOutsideClick: false,
				  allowEscapeKey: false,
	    		  preConfirm: () => {
	    			  var data = $("#login").serialize();
	    			  return fetch('/Maceo/users/ValidateLogin.do', {
	    				    method: 'post',
	    				    headers: {
	    				      "Content-type": "application/x-www-form-urlencoded; charset=UTF-8",
	    				      Accept: 'application/json'
	    				    },
	    				    body: data
	    				  })
	    				  .then(function(response) {
							        if (!response.ok) {
							        	throw response.json();
							        }
							        return response;
							    })
		    		      .then(response => {
		    		    	return response.json();
		    		      }).catch(error => {
		    		    	  console.log(error);
		    		    	  error.then(function(err){
		    		    		   Swal.showValidationMessage(
	    		    		          `Error: ${err.error}`
	    		    		        )
		    		    	  })
		    		      })
	    			 
	    		  },
	    		}).then((result) => {
	    			  if (result.value) {
	    				  //Todo bien entonces --> A pagina de redireccion
	    				    Swal.fire({
	    				      title:  $.i18n.prop('redirecting')
	    				    }).then(function(){
	    				    	window.location.href = url;
	    				    });
	    				  }
	    				})
	    }else if (type === 'registerNeeded') {
	    	Swal.fire({
	    		  title: title,
	    		  type: 'warning',
	    		  html:	textToShow +
		    		    '<form id="registroPopUp"><input id="nombre" name="nomber" class="swal2-input" placeholder="Nombre" required>'+
		    		    '<input id="apellido" name="apellido" class="swal2-input" placeholder="Apellido" required>' +
		    		    '<input id="dni" name="dni" class="swal2-input" placeholder="DNI" required>' +
		    		    '<input id="username" name="username" class="swal2-input" placeholder="Usuario" required>' +
		    		    '<input id="email" name="email" class="swal2-input" placeholder="Email" required>' +
		    		    '<input id="password" type="password1" name="password1" class="swal2-input" placeholder="Contrase\u00f1a" required>'+
		    		    '<input id="password" type="password2" name="password2" class="swal2-input" placeholder="VerificarContrase\u00f1a" required></form>',
		    	  showCancelButton: true,
	    		  confirmButtonText: $.i18n.prop('login'),
	    		  cancelButtonText: $.i18n.prop('cancel'),
	    		  confirmButtonColor: '#28a745',
	    		  showLoaderOnConfirm: true,
	    		  allowOutsideClick: false,
				  allowEscapeKey: false,
	    		  preConfirm: () => {
	    			  var data = $("#registroPopUp").serialize();
	    			  return fetch('/Maceo/users/Registrar.do', {
	    				    method: 'post',
	    				    headers: {
	    				      "Content-type": "application/x-www-form-urlencoded; charset=UTF-8",
	    				      Accept: 'application/json'
	    				    },
	    				    body: data
	    				  })
	    				  .then(function(response) {
							        if (!response.ok) {
							        	throw response.json();
							        }
							        return response;
							    })
		    		      .then(response => {
		    		    	return response.json();
		    		      }).catch(error => {
		    		    	  console.log(error);
		    		    	  error.then(function(err){
		    		    		   Swal.showValidationMessage(
	    		    		          `Error: ${err.error}`
	    		    		        )
		    		    	  })
		    		      })
	    			 
	    		  },
	    		}).then((result) => {
	    			  if (result.value) {
	    				  //Todo bien entonces --> A pagina de redireccion
	    				    Swal.fire({
	    				      title:  $.i18n.prop('redirecting')
	    				    }).then(function(){
	    				    	window.location.href = url;
	    				    });
	    				  }
	    				})
	    }else if ('redirect'){
	    	Swal.fire({
				  title: title,
				  text: textToShow,
				  type: icon,
				  allowOutsideClick: false,
				  allowEscapeKey: false,
				  confirmButtonClass: "btn btn-"+result
		    	})
		    	.then(
	                // handling the promise rejection
	                function(dismiss) {
	                  window.location.href=url;
	                }
	              )
	    }
  }
   showSwalMsg = function(type, comID, userID, prodModel) {
	   Swal.fire({
 		  title: '',
 		  type: 'question',
 		  html:	'<form id="msg">' +
	    		    '<textarea name="message" rows="4" cols="50" required>'+$.i18n.prop('message')+'</textarea>'+
	    		    '<input type="hidden" name="comercioID" value="'+comID+'"/>'+
	    		    '<input type="hidden" name="prodModel" value="'+prodModel+'"/>'+
	    		    '<input type="hidden" name="userID" value="'+userID+'"/></form>',
 		  showCancelButton: true,
 		  confirmButtonText: $.i18n.prop('send'),
 		  cancelButtonText: $.i18n.prop('cancel'),
 		  confirmButtonColor: '#28a745',
 		  showLoaderOnConfirm: true,
 		  allowOutsideClick: false,
			  allowEscapeKey: false,
 		  preConfirm: () => {
 			  var data = $("#msg").serialize();
 			  return fetch('/Maceo/productos/Mensaje.do', {
 				    method: 'post',
 				    headers: {
 				      "Content-type": "application/x-www-form-urlencoded; charset=UTF-8",
 				      Accept: 'application/json'
 				    },
 				    body: data
 				  })
 				  .then(function(response) {
						        if (!response.ok) {
						        	throw response.json();
						        }
						        return response;
						    })
	    		      .then(response => {
	    		    	return response.json();
	    		      }).catch(error => {
	    		    	  console.log(error);
	    		    	  error.then(function(err){
	    		    		  Swal.fire({
	    	 				      title:  $.i18n.prop(err.error),
	    	 				      type: 'warning',
	    	 				    })

	    		    	  })
	    		      })
 			 
 		  },
 		})
 		.then((result) => {
 			  if (result.value) {
 				    Swal.fire({
 				      title:  $.i18n.prop('sentMessage')
 				    })
 				  }
 				})
 
   }
});