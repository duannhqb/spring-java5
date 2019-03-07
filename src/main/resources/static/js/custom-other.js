//$('#seach-box').click(function() {
//	$(this).removeClass("autocomplete-display");
//});
//
//
//$( "#seach-field" ).focus(function() {
//	$("#cls").removeClass("autocomplete-display");
//});
//
//$("#seach-field").blur(function(){
//	$("#cls").addClass("autocomplete-display");
//});

$(document).ready(function() {
	$("#seachfield").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/api/v2/search",
				dataType : "json",
				data : {
					q : request.term
				},
				success : function(data) {
					response(data);
				}
			});
		},
		minLength : 2,
		select : function(event, ui) {
//			log("Selected: " + ui.item.value + " aka " + ui.item.id);
		}
	});
})