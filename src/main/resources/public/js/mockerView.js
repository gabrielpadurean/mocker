$(document).ready(function() {
    $('.sidenav').sidenav();

	$('select').formSelect();
	
	var data = getResults();
	hideResultsLoader();
	showResultsTable(data);
	
	$("#filterForm").submit(function(event) {
		hideResultsTable();
		showResultsLoader();
		
		var data = getResults();
		
		hideResultsLoader();
		showResultsTable(data);
	});
});

function getResults() {
	var nameValue = $("#name").val();
	var endpointValue = $("#endpoint").val();
};

function showResultsLoader() {
	$('#resultsLoader').show();
};

function hideResultsLoader() {
	$('#resultsLoader').hide();
};

function showResultsTable(data) {
	
};

function hideResultsTable() {
	
};