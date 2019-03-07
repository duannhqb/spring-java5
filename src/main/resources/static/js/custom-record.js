// process jquery ajax from UI to controller and opposite

//staff
$("body").on("click", ".page-link-record", function(){
	var page = $(this).data("columns");
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
			url: "/api/v3/list-record",
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
					result += "<td scope=\"row\" style=\"padding-top: 20px;\"><span";
					result += " class=\"status-p bg-primary\">"+ data[i].id + "</span></td>";
					result += "<td style=\"padding-top: 20px;\">"+data[i].staffs.name+"</td>";
					
					if(lang===0){						
						if(data[i].type==true){
							result += "<td style=\"padding-top: 20px;\">Achievements</td>";							
						}else{
							result += "<td style=\"padding-top: 20px;\">Discipline</td>";
						}
					}else{
						if(data[i].type==true){
							result += "<td style=\"padding-top: 20px;\">Thành tích</td>";							
						}else{
							result += "<td style=\"padding-top: 20px;\">Kỷ luật</td>";
						}
					}
					
					result += "<td style=\"padding-top: 20px;\">"+data[i].reason+"</td>";
					result += "<td style=\"padding-top: 20px;\">"+data[i].date+"</td>";
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
})

$("body").on("click", "#btnAddRecord", function(){
	var staffId = $('#staffId').val();
	var type = $('#type').val();
	var reason = $('#reason').val();
	
	var obj = {
		staffId : staffId,
		type : type,
		reason : reason
	}
	
	$.ajax({
		url: "/api/v3/add-record",
		type: "GET",
		dataType: "json",
		contentType: "application/json",
		data: obj,
		success: function(){
				$("#modal").modal('toggle');
		},
		error: function(){
				$("#modal").modal('toggle');
		},
	})
})