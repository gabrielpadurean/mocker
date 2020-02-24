$(document).ready(function() {
    $('.sidenav').sidenav();
	$('select').formSelect();
    $('.modal').modal();
	
	loadResults(0, 20);
	
	$("#filterForm").submit(function(event) {
		event.preventDefault();

		loadResults(0, 20);
	});
});

const resultsTableStart = '<table id="resultsTable" class="highlight responsive-table"><thead><tr><th>Name</th><th>Description</th><th>Endpoint</th><th>Method</th><th>Status</th><th>Body</th><th>Actions</th></tr></thead><tbody>';
const resultsTableEnd = '</tbody></table>';
const resultsPaginationStart = '<ul id="resultsPagination" class="pagination">';
const resultsPaginationEnd = '</ul>';

function loadResults(pageValue, pageSize) {
	hideResultsTable();
	showResultsLoader();
	
	var url = $('#filterForm').attr("action");
	var method = $('#filterForm').attr("method");

	var nameValue = $("#name").val();
	var endpointValue = $("#endpoint").val();
	
	$.ajax({
		url: url,
		type: method,
		data: {
			name: nameValue,
			endpoint: endpointValue,
			page: pageValue,
			size: pageSize
		}
	})
	.done(function(data) {
		hideResultsLoader();
		showResultsTable(data, pageValue, pageSize);
	})
	.fail(function(data) {
		hideResultsLoader();
		
		$('#modalTitle').html("Error");
		$('#modalContent').html("Mappings could not be retrieved due to the following error: " + data.responseJSON.message);
		$('.modal').modal('open');
	});
};

function deleteResult(id) {
	$.ajax({
		url: '/v1/api/mappings/' + id,
		type: 'delete'
	})
	.done(function(data) {
		loadResults(0, 20);
	})
	.fail(function(data) {
		$('#modalTitle').html("Error");
		$('#modalContent').html("Mapping could not be deleted due to the following error: " + data.responseJSON.message);
		$('.modal').modal('open');
		
		loadResults(0, 20);
	});
};

function hideResultsLoader() {
	$('#resultsLoader').hide();
};

function showResultsLoader() {
	$('#resultsLoader').show();
};

function hideResultsTable() {
	$('#resultsTable').remove();
	$('#resultsPagination').remove();
};

function showResultsTable(mappings, pageValue, pageSize) {
	var resultsTable = '';
	var resultsPagination = '';
	var index;
	
	for (index in mappings) {
		resultsTable += '<tr><td>' + 
			mappings[index].name + '</td><td>' + 
			mappings[index].description + '</td><td>' + 
			mappings[index].request.endpoint + '</td><td>' + 
			mappings[index].request.method + '</td><td>' + 
			mappings[index].response.status + '</td><td>' + 
			mappings[index].response.body + '</td><td><a href="javascript:;" title="Update mapping" class="actionIcon"><i class="material-icons">edit</i></a><a href="javascript:deleteResult(' + mappings[index].id + ');" title="Delete mapping" class="actionIcon"><i class="material-icons">delete</i></a></td></tr>';
	}
	
	$('#resultsContainer').append(resultsTableStart + resultsTable + resultsTableEnd);
	$('#resultsContainer').append(resultsPaginationStart + resultsPagination + resultsPaginationEnd);
};