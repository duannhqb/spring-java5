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
			
			for(var i = 0; i < staff.length; i++){
				result += "<tr>";
				result += "<td scope=\"row\" style=\"padding-top: 40px;\"><span";
				result += " class=\"status-p bg-primary\">"+ staff[i].id + "</span></td>";
				result += "<td><img src=\"/upload/"+staff[i].photo+"\"";
				result += "style=\"height: 100px; width: 100px;\" /></td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].name+"</td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].phone+"</td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].departs.name+"</td>";
				result += "<td style=\"padding-top: 40px;\">";
				result += "<a href=\"#viewModal\" role=\"button\" data-toggle=\"modal\" data-idstaff=\"" + staff[i].id + "\" class=\"btn btn-sm btn-outline-success mr-2 btn-idstaff\"><i class=\"fas fa-eye\"></i></a>";
				result += "<a href=\"/staff/update-staff/" + staff[i].id + "\" ";
				result += "class=\"btn btn-sm btn-primary\"><i class=\"fa fa-edit\"></i></a>";
				result += "<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"";
				result += " class=\"btn btn-sm btn-danger ml-2 btn-delete-staff\"><i";
				result += " class=\"fa fa-trash\"></i></a></td>";
				result += "</tr>";
			}
			
			var tbody = $("#tableStaff").find("tbody");
			tbody.empty();
			tbody.html(result);
			
			// set page
			$("#indexPaging").data("page", page);
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
	    	$("#photophotoHistory").val(value);	    	
	    	$('#photoModal').modal('toggle');
	    },
	    error: function () {
	    	$('#photoModal').modal('toggle');
	    }
	  });
})

// view modal when click button
$("body").on("click", ".btn-idstaff", function(){
	var id = $(this).data("idstaff");
	var lang = 0;
	
	$.ajax({
		url: "/api/v2/getLang",
		type: "GET",
		dataType: "json",
		contentType: "application/json",
		success: function(value){
			if(value === 0){
//				is english
				lang = 0;
			}else{
				lang = 1;
			}
		}
	}).done(function(){
		$.ajax({
			url: "/api/v2/get-staff",
			type: "GET",
			dataType: "json",
			contentType: "application/json",
			data: {
				id: id
			},
			success: function(staff){
				$("#viewImage").attr("src", "/upload/" + staff.photo);
				$("#name").val(staff.name);
				$("#phone").val(staff.phone);
				$("#email").val(staff.email);
				$("#birthday").val(staff.birthDay);
				
				if(lang===1){
					if(staff.gender===true){
						$("#gender").val("Nam");
					}else{
						$("#gender").val("Nữ");
					}
				}else{
					if(staff.gender===true){
						$("#gender").val("Male");
					}else{
						$("#gender").val("Female");
					}
				}
				
				$("#departs").val(staff.departs.name);
				$("#salary").val(staff.salary);
				$("#notes").val(staff.notes);
			}
		})
	});
})


// close modal view staff
$("body").on("click", ".modalCs", function(){
	$("#viewModal").modal('toggle');
})

// get id to delete staff
var iddeletestaff = null;

$("body").on("click", ".btn-delete-staff", function(){
	iddeletestaff = $(this).closest("tr").find(".btn-idstaff").data("idstaff");
})	

// delete staff on list
$("body").on("click", "#btnDelete", function(){
	var page = $("#indexPaging").data("page");
	
	$.ajax({
		url: "/api/v2/delete-staff",
		type: "GET",
		dataType: "json",
		contentType: "application/json",
		data: {
			id: iddeletestaff,
			page: page
		},
		success: function(staff){
//			đóng modal
			$("#myModal").modal('toggle');
			
			var result = "";
			
			for(var i = 0; i < staff.length; i++){
				result += "<tr>";
				result += "<td scope=\"row\" style=\"padding-top: 40px;\"><span";
				result += " class=\"status-p bg-primary\">"+ staff[i].id + "</span></td>";
				result += "<td><img src=\"/upload/"+staff[i].photo+"\"";
				result += "style=\"height: 100px; width: 100px;\" /></td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].name+"</td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].phone+"</td>";
				result += "<td style=\"padding-top: 40px;\">"+staff[i].departs.name+"</td>";
				result += "<td style=\"padding-top: 40px;\">";
				result += "<a href=\"#viewModal\" role=\"button\" data-toggle=\"modal\" data-idstaff=\"" + staff[i].id + "\" class=\"btn btn-sm btn-outline-success mr-2 btn-idstaff\"><i class=\"fas fa-eye\"></i></a>";
				result += "<a href=\"/staff/update-staff/" + staff[i].id + "\" ";
				result += "class=\"btn btn-sm btn-primary\"><i class=\"fa fa-edit\"></i></a>";
				result += "<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\"";
				result += " class=\"btn btn-sm btn-danger ml-2 btn-delete-staff\"><i";
				result += " class=\"fa fa-trash\"></i></a></td>";
				result += "</tr>";
			}
			
			var tbody = $("#tableStaff").find("tbody");
			tbody.empty();
			tbody.html(result);

			// set page
			$("#indexPaging").data("page", page);
		}
	}).done(function(){
		iddeletestaff = null;
	})
})