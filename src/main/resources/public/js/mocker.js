$(document).ready(function() {
	$('select').formSelect();

	$("#createMappingForm").submit(function(event) {
		event.preventDefault();

		var url = $(this).attr("action");
		var method = $(this).attr("method");
		var name = $("#name").val();
		var description = $("#description").val();

		$.ajax({
			url: url,
			type: method,
			dataType: 'json',
			data: {
				name: name,
				description: description
			},
		})
		.done(function(data) {
			alert("Done: " + data);
		})
		.fail(function(data) {
			alert("Fail: " + data);
		});
	});
});