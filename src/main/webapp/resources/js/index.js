/**
 * 
 */


$('document').ready(function() {
	//alert("DOM Ready!");
	/*
	$('#link-search-friend').click(function() {
		$( "#form-add-friends" ).toggle( "slow" );
	});*/
	
	//Ajax Function For Retrieving Friend List
	
	
});

function ajaxFunction(searchKey){
	//alert("sending request");
	var request = $.ajax({
		  url: "addFriendSearchUsername.htm",
		  method: "GET",
		  data: {username:searchKey},
		  dataType: "json"
		});
		 
		request.done(function( msg ) {
			//alert(msg);
		  //$( "#result-search-add-freinds" ).html( msg );
			$('#result-search-add-freinds').append("<ul class=\"list-group\">");
			
			$.each(msg, function (idx, value) {
				var toAppend = '<a class=\"list-group-item\"';
				toAppend += 'href=\"/main/showProfile.htm?username=';
				toAppend += value + '\">';
				toAppend += value;
				toAppend += '</a>';
				$('#result-search-add-freinds').append(toAppend);
				
			});
			
			$('#result-search-add-freinds').append("</ul>");
			
		});
}  