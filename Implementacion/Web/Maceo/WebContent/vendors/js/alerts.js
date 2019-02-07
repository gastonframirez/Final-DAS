$(function($) {
  showSwal = function(type) {
    'use strict';
    if (type === 'logged-in') {
      swal({
        title: 'Te estamos llevando a NOMBRE DE COMERCIO para que compres el producto:',
        text: 'DATOS DEL PRODUCTO',
        icon: 'success',
        timer: 4000,
        closeOnClickOutside: false,
        closeOnEsc: false,
        button: false
        }).then(
          // handling the promise rejection
          function(dismiss) {
            console.log('Cerrado por timer')
            window.location.href="/Maceo";
          }
        )
    } else if (type === 'need-login') {
      swal({
        title: 'ACA VA EL LOGIN!',
        text: 'Click OK to close this alert',
        button: {
          text: "OK",
          value: true,
          visible: true,
          className: "btn btn-primary"
        }
      })


    } else if (type === 'agregado-correcto') {
        swal({
            title: $.i18n.prop('successfully-added'),
            text: 'Click OK to close this alert',
            button: {
              text: "OK",
              value: true,
              visible: true,
              className: "btn btn-primary"
            }
          })


    } else if (type === 'error') {
        swal({
            title: $.i18n.prop('error-tit'),
            text: $.i18n.prop('error-msg'),
            button: {
              text: "OK",
              value: true,
              visible: true,
              className: "btn btn-primary"
            }
          })


    }
    else if (type === 'custom-html') {
      swal({
        
        button: {
          text: "OK",
          value: true,
          visible: true,
          className: "btn btn-primary"
        }
      })
    }
  }

  showSwal = function(type, result, title, textToShow, url, icon) {
	  'use strict';
	    if (type === 'success') {
	    	Swal.fire({
			  title: title,
			  text: textToShow,
			  type: icon,
			  allowOutsideClick: false,
			  allowEscapeKey: false,
			  confirmButtonClass: "btn btn-"+result
	    	})
//	    	.then(
//                // handling the promise rejection
//                function(dismiss) {
//                  window.location.href=url;
//                }
//              )

	    } else if (type === 'loginNeeded') {
	    	Swal.fire({
	    		  title: title,
	    		  type: 'warning',
	    		  html:	'Necesitas ingresar para proseguir' +
		    		    '<form id="login"><input id="username" name="username" class="swal2-input" placeholder="Usuario">' +
		    		    '<input id="password" type="password" name="password" class="swal2-input" placeholder="Contrase\u00f1a"></form>',
	    		  showCancelButton: true,
	    		  confirmButtonText: 'Ingresar',
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
	    		      .then(response => {
//	    		    	console.log(response.clone().json());
//	    		    	response.clone().json().then(function(res) {
//	    		    	    if (res.error) {
//	    		    	    	Swal.showValidationMessage(
//	    		    	    			`Error: ${res.error}`
//			    		        )
//	  	    		        }else{
//	  	    		        	return res;
//	  	    		        }
//	    		    	    
//	    		    	});
	    		    	return response.json();
	    		      }).catch(error => {
	    		    	  alert("AAAAA");
	    		        Swal.showValidationMessage(
	    		          `Request failed: ${error}`
	    		        )
	    		      })
	    			 
	    		  },
	    		}).then((result) => {
	    			  if (result.value) {
	    				    Swal.fire({
	    				      title: `${result.value.login}'s avatar`,
	    				      imageUrl: result.value.avatar_url
	    				    })
	    				  }
	    				})
	    }
  }
//  showSwal = function(type, result, title, textToShow, url, icon ) {
//	    'use strict';
//	    if (type === 'agregado-correcto') {
//	        swal({
//	            title: title,
//	            text: textToShow,
//	            closeOnClickOutside: false,
//	            closeOnEsc: false,
//	            button: false,
//	            icon: icon,
//	            button: {
//	              text: "OK",
//	              value: true,
//	              visible: true,
//	              className: "btn btn-"+result
//	            }
//	        }).then(
//	                // handling the promise rejection
//	                function(dismiss) {
//	                  window.location.href=url;
//	                }
//	              )
//
//
//	    } else if (type === 'error') {
//	    	swal({
//	            title: title,
//	            text: textToShow,
//	            closeOnClickOutside: false,
//	            closeOnEsc: false,
//	            button: false,
//	            icon: icon,
//	            button: {
//	              text: "OK",
//	              value: true,
//	              visible: true,
//	              className: "btn btn-"+result
//	            }
//	        });
//
//	    }
//	  }

});