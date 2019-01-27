$(document).ready(function() {

	 
	 $("#addComercioForm").on("submit", function(e){
		e.preventDefault();
		
		var data = $( "#addComercioForm" ).serialize();
		
		$.ajax({
          url: "/Maceo/admin/AddComercio.do",
          type: "post",
          data: data,
          dataType: "html",
          error: function(hr){
              jUtils.showing("error", hr.error);
          },
          success: function(res) {        	 
        	  console.log(res);
          }
      });
		
		return false;
	 })
});