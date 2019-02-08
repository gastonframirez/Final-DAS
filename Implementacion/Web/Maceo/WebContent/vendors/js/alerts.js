$(function($) {
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
		    		    '<form id="login"><input id="username" name="username" class="swal2-input" placeholder="Usuario">' +
		    		    '<input id="password" type="password" name="password" class="swal2-input" placeholder="Contrase\u00f1a"></form>',
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
	    } else if ('redirect'){
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
});