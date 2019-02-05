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
 
  showSwal = function(type, result, title, textToShow, url, icon ) {
	    'use strict';
	    if (type === 'agregado-correcto') {
	        swal({
	            title: title,
	            text: textToShow,
	            closeOnClickOutside: false,
	            closeOnEsc: false,
	            button: false,
	            icon: icon,
	            button: {
	              text: "OK",
	              value: true,
	              visible: true,
	              className: "btn btn-"+result
	            }
	        }).then(
	                // handling the promise rejection
	                function(dismiss) {
	                  window.location.href=url;
	                }
	              )


	    } else if (type === 'error') {
	        swal({
	            title: title,
	            text: textToShow,
	            button: {
	              text: "OK",
	              value: true,
	              visible: true,
	              className: "btn btn-"+result
	            }
	          })


	    }
	  }

});