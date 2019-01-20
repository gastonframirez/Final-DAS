$(function($) {
  showSwal = function(type) {
    'use strict';
    if (type === 'logged-in') {
      swal({
        title: 'Te estamos llevando a NOMBRE DE COMERCIO para que compres el producto:',
        text: 'DATOS DEL PRODUCTO',
        icon: 'success',
        timer: 3000,
        closeOnClickOutside: false,
        closeOnEsc: false,
        button: false
        }).then(
          // handling the promise rejection
          function(dismiss) {
            console.log('I was closed by the timer')
            window.location.href="file:///Users/gframirez/Documents/UBP/Q10/DAS/Final-DAS/Implementacion/Maquetas/Dashboard-Home/index-cliente.html";
          }
        )
    } else if (type === 'need-login') {
      swal({
        title: 'Read the alert!',
        text: 'Click OK to close this alert',
        button: {
          text: "OK",
          value: true,
          visible: true,
          className: "btn btn-primary"
        }
      })


    } else if (type === 'custom-html') {
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

});