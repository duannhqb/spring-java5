// process jquery ajax from UI to controller and opposite

//staff
$("body").on("click", ".page-link", function(){
	var page = $(this).data("columns");
	
	$.ajax({
		url: "/api/v2/list-staff",
		type: "GET",
		dataType: "json",
		contentType: "application/json",
		data: {
			page: page,
		},
		success: function(staff){
			var result = "";
			var ss = "";
			
			for(var i = 0; i < staff.length; i++){
				result += "<tr>";
				result += "<td scope=\"row\" style=\"padding-top: 40px;\"><span";
				result += " class=\"status-p bg-primary\">"+ staff[i].id + "</span></td>";
				result += "<td><img src=\"/upload/"+staff[i].photo+"\"";
				result += "style=\"height: 100px; width: 100px;\" /></td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].name+"</td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].phone+"</td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].departs.name+"</td>";
				result += "<td style=\"padding-top: 40px;\"><a";
				result += " href=\"/staff/update-staff/" + staff[i].id + "\" ";
				result += "class=\"btn btn-sm btn-primary\"><i class=\"fa fa-edit\"></i></a>";
				result += "<a href=\"#myModal_"+staff[i].id+"\" role=\"button\" data-toggle=\"modal\"";
				result += " class=\"btn btn-sm btn-danger ml-2\"><i";
				result += " class=\"fa fa-trash\"></i></a></td>";
				result += "</tr>";
				
				ss += "<div id=\"myModal_"+staff[i].id+"\" class=\"modal fade\">";
				ss += "<div class=\"modal-dialog\">";
				ss += "<div class=\"modal-content\">";
				ss += "<div class=\"modal-header\">";
				ss += "<h6 class=\"modal-title\">Confirm Delete</h6>";
				ss += "</div>";
				ss += "<div class=\"modal-body\">";
				ss += "<p>Are you sure you want to delete this staff?</p>"; 
				ss += "</div>";
				ss += "<div class=\"modal-footer\">";
				ss += "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>";
				ss += "<a href=\"/staff/remove-staff/" + staff[i].id + "\" class=\"btn btn-outline-danger\">";
				ss += "<i class=\"fa fa-trash-o mr-1\"></i>Delete</a>";
				ss += "</div>";
				ss += "</div>";
				ss += "</div>";
				ss += "</div>";
			}
			
			var tbody = $("#tableStaff").find("tbody");
			tbody.empty();
			tbody.html(result);
			
			var ssss = $(".single-table").find(".table-responsive");
			ssss.find(".modal").remove();
			
			ssss.append(ss);
			
		}
	})
})

// upload image

var files = [];
$('#fileImage').on("change", function(event){
	
	files = event.target.files;
	forms = new FormData();
	forms.append("file", files[0]);
	
	$.ajax({
	    url: "/api/v2/uploadFile",
	    type: "POST",
	    data: forms,
	    enctype: 'multipart/form-data',
	    processData: false,
	    contentType: false,
	    cache: false,
	    success: function (value) {
	    	$("#photoDisplay").html("");
	    	$("#photoDisplay").html("<img src=\"/upload/" + value + "\" style=\"height: 150px; width: 150px;\" />");
	    	$('#photoModal').modal('toggle');
	    },
	    error: function () {
	    	$('#photoModal').modal('toggle');
	    }
	  });
})