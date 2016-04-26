/**
 * 
 */

//$('.datepicker').datepicker({
//    format: 'mm/dd/yyyy',
//    startDate: '-3d'
//});

$('document').ready(function() {
	$("#birthDateAPI").click(function() {
		$('.datepicker').datepicker({
			format: 'mm/dd/yyyy',
			startDate: '-3d'
		});
	});
	
	$('#input_retype_password').focusout(function() {
		var $retypePassword = $('#input_retype_password').val();
		
		var $password = $('#input_password').val();
		if($retypePassword == $password){
			$('#error-passowrd').html("");
		}
		else{
			$('#error-passowrd').html("Passwords Do Not Match");
		}
	})
});

