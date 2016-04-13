/**
 * 
 */

$('document').ready(function(){
	'use strict';
	
	//alert("DOM Loaded");
	/*
	var login_form = $('#login-form').submit(function(ev) {
		ev.preventDefault();

		try{
		$.ajax({
				type: login_form.attr('method'),
				url: login_form.attr('action'),
				data: login_form.serialize(),
				success: function (dataServer) {
					try{
						var jsonObj = JSON.parse(dataServer);
					}
					catch(e){
						$("#errorx").html("Username & Password Do Not Match!");
					}
					
					window.location='http://localhost:8080/main/authenticated.htm/TOKEN/' + jsonObj.TOKEN;
				},
				error: function(){
					$("#errorx").html("Username & Password Do Not Match!");
					//$( "#log-in-modal" ).effect( "bounce", "slow" );
				}
		});
		}catch(e){
			$("#errorx").html("Username & Password Do Not Match!");
			if(typeof(console) !== undefined)
				console.log(e);
		}
		return false;
	});
	*/
});

