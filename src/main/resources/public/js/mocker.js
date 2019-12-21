$(document).ready(function() {
	$('select').formSelect();
	
    $('.modal').modal();

	$("#createMappingForm").submit(function(event) {
		event.preventDefault();

		var url = $(this).attr("action");
		var method = $(this).attr("method");
		var nameValue = $("#name").val();
		var descriptionValue = $("#description").val();
		var endpointValue = $("#endpoint").val();
		var methodValue = $("#method").val();
		var statusValue = $("#status").val();
		var bodyValue = $("#body").val();
		
		var mapping = {
			name : nameValue,
			description : descriptionValue,
			request: {
				endpoint: endpointValue,
				method: methodValue
			},
			response: {
				status: statusValue,
				body: bodyValue
			}
		}
		
		$.ajax({
			url: url,
			type: method,
			dataType: 'json',
		    contentType: 'application/json;charset=UTF-8',
			data: JSON.stringify(mapping)
		})
		.done(function(data) {
			$('#modalTitle').html("Info");
			$('#modalContent').html("The mapping was created successfully!");
			$('.modal').modal('open');
		})
		.fail(function(data) {
			$('#modalTitle').html("Error");
			$('#modalContent').html("The mapping cannot be created: " + data.responseJSON.message);
			$('.modal').modal('open');
		});
	});
});