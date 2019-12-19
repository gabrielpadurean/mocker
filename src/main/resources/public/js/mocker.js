$(document).ready(function() {
	$('select').formSelect();

	$('.tooltipped').tooltip();

	$("#createMapping").click(function(event) {
		event.preventDefault();

		var name = $("#name").val();
		var description = $("#description").val();

		$.ajax({
			type: 'POST',
			url: 'https://localhost:8080/v1/api/mappings',
			data: {
				name: name,
				description: description
			},
			dataType: 'json'
		})
		.done(function(data) {
			alert("Done: " + data);
		})
		.fail(function(data) {
			alert("Fail: " + data);
		});
	});
});