/**
 * 
 */

$('.datepicker').datepicker({
    format: 'mm/dd/yyyy',
    startDate: '-3d'
});

$('document').ready(function() {
	$("#birthDateAPI").click(function() {
		$('.datepicker').datepicker({
			format: 'mm/dd/yyyy',
			startDate: '-3d'
		});
	});
});

