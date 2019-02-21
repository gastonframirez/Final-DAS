$(document).ready(function() {
	
	function hideSearch(){
		$("body").removeClass("loaderActive");
		$("#searchStr").val('');
	}
	
	$(".searchClick").on("click", function(e){
		e.preventDefault();
		$("body").addClass("loaderActive");
	})
	$(".closeSearch").on("click", function(e){
		e.preventDefault();
		hideSearch();
	})
	
	$(document).keyup(function(e) {
		  if (e.keyCode === 27){
			  hideSearch();
		  }
	});

	$(".searchIcon").on("click", function(e){
		e.preventDefault();
		$("form.searchForm").submit();
	})

});
