// process jquery ajax from UI to controller and opposite

//staff
$("body").on("click", ".page-link-user", function(){
	var page = $(this).data("columns");
		$.ajax({
			url: "/api/v4/list-user",
			type: "GET",
			dataType: "json",
			contentType: "application/json",
			data: {
				page: page,
			},
			success: function(data){
				var result = "";
				
				for(var i = 0; i < data.length; i++){
					result += "<tr>";
					result += "<td scope=\"row\" style=\"padding-top: 20px;\">"+ data[i].fullName + "</td>";
					result += "<td style=\"padding-top: 20px;\">"+ data[i].username +"</td>";
					result += "<td style=\"padding-top: 20px;\">" + data[i].password + "</td>";
					result += "<td>";
					result += "<a href=\"#modalEdit\" role=\"button\" data-toggle=\"modal\" data-username=\""+ data[i].username +"\" class=\"btn btn-sm btn-success ml-2 btn-edit-user\"><i class=\"fa fa-edit\"></i></a>";
					result += "<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" data-username=\"" + data[i].username + "\"";
					result += " class=\"btn btn-sm btn-danger ml-2 btn-delete-user\"><i";
					result += " class=\"fa fa-trash\"></i></a></td>";
					result += "</tr>";
				}
				
				var tbody = $("#tableStaff").find("tbody");
				tbody.empty();
				tbody.html(result);
			}
	})
})

// add user
//$("body").on("click", "#btnAddUser", function(){
//	var fullName = $('#name').val();
//	var username = $('#username').val();
//	var password = $('#password').val();
//	
//	$.ajax({
//		url: "/api/v4/add-user",
//		type: "GET",
//		dataType: "json",
//		contentType: "application/json",
//		data: {
//			fullName : fullName,
//			username : username,
//			password : password
//		},
//		success: function(data){
//			$("#modal").modal('toggle');
//			
//			$('#name').val("");
//			$('#username').val("");
//			$('#password').val("");
//			
//			
//			var result = "";
//			
//			for(var i = 0; i < data.length; i++){
//				result += "<tr>";
//				result += "<td scope=\"row\" style=\"padding-top: 20px;\">"+ data[i].fullName + "</td>";
//				result += "<td style=\"padding-top: 20px;\">"+ data[i].username +"</td>";
//				result += "<td style=\"padding-top: 20px;\">" + data[i].password + "</td>";
//				result += "<td>";
//				result += "<a href=\"#modalEdit\" role=\"button\" data-toggle=\"modal\" data-username=\""+ data[i].username +"\" class=\"btn btn-sm btn-success ml-2 btn-edit-user\"><i class=\"fa fa-edit\"></i></a>";
//				result += "<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" data-username=\"" + data[i].username + "\"";
//				result += " class=\"btn btn-sm btn-danger ml-2 btn-delete-user\"><i";
//				result += " class=\"fa fa-trash\"></i></a></td>";
//				result += "</tr>";
//			}
//			
//			var tbody = $("#tableStaff").find("tbody");
//			tbody.empty();
//			tbody.html(result);
//		},
//		error: function(){
//			$("#modal").modal('toggle');
//		}
//	})
//})

// set id to edit
//var idEdit = null;
//$("body").on("click", ".btn-edit-user", function(){
//	idEdit = $(this).data('username');
//	
//	$.ajax({
//		url: "/api/v4/get-user",
//		type: "GET",
//		dataType: "json",
//		contentType: "application/json",
//		data: { 
//			username : idEdit
//		},
//		success: function(data){
//			$('#nameEdit').val(data.fullName);
//			$('#usernameEdit').val(data.username);
//			$('#passwordEdit').val(data.password);
//		},
//		error: function(){
//			$("#modalEdit").modal('toggle');
//		}
//	})
//	
//	idEdit = null;
//})
//
//// update user
//$("body").on("click", "#btnUpdateUser", function(){
//	var fullName = $('#nameEdit').val();
//	var password = $('#passwordEdit').val();
//	var username = $('#usernameEdit').val();
//	
//	var obj = {
//		fullName : fullName,
//		username : username,
//		password : password
//	}
//	
//	$.ajax({
//		url: "/api/v4/edit-user",
//		type: "GET",
//		dataType: "json",
//		contentType: "application/json",
//		data: obj,
//		success: function(data){
//			$("#modalEdit").modal('toggle');
//			
//			var result = "";
//			
//			for(var i = 0; i < data.length; i++){
//				result += "<tr>";
//				result += "<td scope=\"row\" style=\"padding-top: 20px;\">"+ data[i].fullName + "</td>";
//				result += "<td style=\"padding-top: 20px;\">"+ data[i].username +"</td>";
//				result += "<td style=\"padding-top: 20px;\">" + data[i].password + "</td>";
//				result += "<td>";
//				result += "<a href=\"#modalEdit\" role=\"button\" data-toggle=\"modal\" data-username=\""+ data[i].username +"\" class=\"btn btn-sm btn-success ml-2 btn-edit-user\"><i class=\"fa fa-edit\"></i></a>";
//				result += "<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" data-username=\"" + data[i].username + "\"";
//				result += " class=\"btn btn-sm btn-danger ml-2 btn-delete-user\"><i";
//				result += " class=\"fa fa-trash\"></i></a></td>";
//				result += "</tr>";
//			}
//			
//			var tbody = $("#tableStaff").find("tbody");
//			tbody.empty();
//			tbody.html(result);
//		},
//		error: function(msg){
//			$('#msg').html("<div class=\"alert alert-danger\" role=\"alert\"> Had errors, please check again! </div>");
//		}
//	})
//})

// set id to delete
var idDelete = null;
$("body").on("click", ".btn-delete-user", function(){
	idDelete = $(this).data('username');
})

// delete user
$("body").on("click", "#btnDeleteUser", function(){
	$.ajax({
		url: "/api/v4/delete-user",
		type: "GET",
		dataType: "json",
		data: { username : idDelete },
		contentType: "application/json",
		success: function(data){
			$("#myModal").modal('toggle');
			
			var result = "";
			
			for(var i = 0; i < data.length; i++){
				result += "<tr>";
				result += "<td scope=\"row\" style=\"padding-top: 20px;\">"+ data[i].fullName + "</td>";
				result += "<td style=\"padding-top: 20px;\">"+ data[i].username +"</td>";
				result += "<td style=\"padding-top: 20px;\">" + data[i].password + "</td>";
				result += "<td>";
				result += "<a href=\"#modalEdit\" role=\"button\" data-toggle=\"modal\" data-username=\""+ data[i].username +"\" class=\"btn btn-sm btn-success ml-2 btn-edit-user\"><i class=\"fa fa-edit\"></i></a>";
				result += "<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" data-username=\"" + data[i].username + "\"";
				result += " class=\"btn btn-sm btn-danger ml-2 btn-delete-user\"><i";
				result += " class=\"fa fa-trash\"></i></a></td>";
				result += "</tr>";
			}
			
			var tbody = $("#tableStaff").find("tbody");
			tbody.empty();
			tbody.html(result);
		},
		error: function(){
				$("#myModal").modal('toggle');
		}
	})
	
	idDelete = null;
})

// validator add
	$("body").on("click", "#btnAddUser", function(){
		var fullName = $('#name').val();
		var username = $('#username').val();
		var password = $('#password').val();
		
		var user = {
			fullName : fullName,
			username : username,
			password : password
		};
		
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/api/v4/validation",
	        data: JSON.stringify(user),
	        dataType: 'json',
	        cache: false,
	        timeout: 600000,
	        success: function (data) {

	        	$("#nameAdd").text("");
	        	$("#usernameAdd").text("");
	        	$("#passwordAdd").text("");
	        	
	        	$.ajax({
	        		url: "/api/v4/add-user",
	        		type: "GET",
	        		dataType: "json",
	        		contentType: "application/json",
	        		data: {
	        			fullName : fullName,
	        			username : username,
	        			password : password
	        		},
	        		success: function(data){
	        			$("#modal").modal('toggle');
	        			
	        			$('#name').val("");
	        			$('#username').val("");
	        			$('#password').val("");
	        			
	        			
	        			var result = "";
	        			
	        			for(var i = 0; i < data.length; i++){
	        				result += "<tr>";
	        				result += "<td scope=\"row\" style=\"padding-top: 20px;\">"+ data[i].fullName + "</td>";
	        				result += "<td style=\"padding-top: 20px;\">"+ data[i].username +"</td>";
	        				result += "<td style=\"padding-top: 20px;\">" + data[i].password + "</td>";
	        				result += "<td>";
	        				result += "<a href=\"#modalEdit\" role=\"button\" data-toggle=\"modal\" data-username=\""+ data[i].username +"\" class=\"btn btn-sm btn-success ml-2 btn-edit-user\"><i class=\"fa fa-edit\"></i></a>";
	        				result += "<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" data-username=\"" + data[i].username + "\"";
	        				result += " class=\"btn btn-sm btn-danger ml-2 btn-delete-user\"><i";
	        				result += " class=\"fa fa-trash\"></i></a></td>";
	        				result += "</tr>";
	        			}
	        			
	        			var tbody = $("#tableStaff").find("tbody");
	        			tbody.empty();
	        			tbody.html(result);
	        		},
	        		error: function(){
	        			$("#modal").modal('toggle');
	        		}
	        	})

	        },
	        error: function (data) {
	        	
	        	$("#nameAdd").text("");
	        	$("#usernameAdd").text("");
	        	$("#passwordAdd").text("");
	        	
	        	jQuery.each(data, function(i, val) {
		        	$("#nameAdd").text(val.fullName);
		        	$("#usernameAdd").text(val.username);
		        	$("#passwordAdd").text(val.password);
	        	});

	        }
	    });
	});

// set id to edit
var idEdit = null;
$("body").on("click", ".btn-edit-user", function(){
	idEdit = $(this).data('username');
	
	$.ajax({
		url: "/api/v4/get-user",
		type: "GET",
		dataType: "json",
		contentType: "application/json",
		data: { 
			username : idEdit
		},
		success: function(data){
			$('#nameEdit').val(data.fullName);
			$('#usernameEdit').val(data.username);
			$('#passwordEdit').val(data.password);
		},
		error: function(){
			$("#modalEdit").modal('toggle');
		}
	})
	
	idEdit = null;
})

// validator edit
$("body").on("click", "#btnUpdateUser", function(){
	var fullName = $('#nameEdit').val();
	var password = $('#passwordEdit').val();
	var username = $('#usernameEdit').val();
	
	var user = {
		fullName : fullName,
		username : username,
		password : password
	};
	
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/v4/validation",
        data: JSON.stringify(user),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

        	$("#nameVaEdit").text("");
        	$("#usernameVaEdit").text("");
        	$("#passwordVaEdit").text("");
        	
        	$.ajax({
        		url: "/api/v4/edit-user",
        		type: "GET",
        		dataType: "json",
        		contentType: "application/json",
        		data: user,
        		success: function(data){
        			$("#modalEdit").modal('toggle');
        			
        			var result = "";
        			
        			for(var i = 0; i < data.length; i++){
        				result += "<tr>";
        				result += "<td scope=\"row\" style=\"padding-top: 20px;\">"+ data[i].fullName + "</td>";
        				result += "<td style=\"padding-top: 20px;\">"+ data[i].username +"</td>";
        				result += "<td style=\"padding-top: 20px;\">" + data[i].password + "</td>";
        				result += "<td>";
        				result += "<a href=\"#modalEdit\" role=\"button\" data-toggle=\"modal\" data-username=\""+ data[i].username +"\" class=\"btn btn-sm btn-success ml-2 btn-edit-user\"><i class=\"fa fa-edit\"></i></a>";
        				result += "<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" data-username=\"" + data[i].username + "\"";
        				result += " class=\"btn btn-sm btn-danger ml-2 btn-delete-user\"><i";
        				result += " class=\"fa fa-trash\"></i></a></td>";
        				result += "</tr>";
        			}
        			
        			var tbody = $("#tableStaff").find("tbody");
        			tbody.empty();
        			tbody.html(result);
        		},
        		error: function(msg){
        			$('#msg').html("<div class=\"alert alert-danger\" role=\"alert\"> Had errors, please check again! </div>");
        		}
        	})

        },
        error: function (data) {
        	
        	$("#nameVaEdit").text("");
        	$("#usernameVaEdit").text("");
        	$("#passwordVaEdit").text("");
        	
        	jQuery.each(data, function(i, val) {
	        	$("#nameVaEdit").text(val.fullName);
	        	$("#usernameVaEdit").text(val.username);
	        	$("#passwordVaEdit").text(val.password);
        	});

        }
    });

});