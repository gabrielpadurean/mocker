$(document).ready(function() {
    $('.sidenav').sidenav();

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
		
		endpointValue = formatEndpoint(endpointValue);
		
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
			$('#modalTitle').html("Success");
			$('#modalContent').html("The mapping was created successfully. Use method <b>" + methodValue.toUpperCase() + "</b> and <b>https://localhost:8080/v1/api/mocks" + endpointValue + "</b> endpoint.");
			$('.modal').modal('open');
		})
		.fail(function(data) {
			$('#modalTitle').html("Error");
			$('#modalContent').html("The mapping cannot be created due to the following error: " + data.responseJSON.message);
			$('.modal').modal('open');
		});
	});
});

function formatEndpoint(endpoint) {
	if (!endpoint.startsWith("/")) {
		return "/" + endpoint;
	} else {
		return endpoint;
	}
};