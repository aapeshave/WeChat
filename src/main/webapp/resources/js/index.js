/**
 * 
 */

var username;
$('document').ready(function() {
	//alert("DOM Ready!");
	/*
	$('#link-search-friend').click(function() {
		$( "#form-add-friends" ).toggle( "slow" );
	});*/	
	
	
	//Ajax call for loading list of friends
	$('#panel-my-friends').on('show.bs.collapse', function () {
		 var $username = $('#userNameTextFieldHidden').val();
		 //alert($username);
		 var request = $.ajax({
			  url: "showFriendList.htm",
			  method: "GET",
			  data: {username:$username},
			  dataType: "json"
			});
			 
			request.done(function( msg ) {
				alert(msg)
				/*$.each(msg, function (idx, value) {
					
				});*/
				
			});
			
			request.error(function(error) {
				alert("error");
			});
	});

	
});

//Ajax Function For Retrieving Friend List
function ajaxFunction(searchKey){
	//alert("sending request");
	if(searchKey.length>3){
		var request = $.ajax({
			  url: "addFriendSearchUsername.htm",
			  method: "GET",
			  data: {username:searchKey},
			  dataType: "json"
			});
			 
			request.done(function( msg ) {
				//alert(msg);
			  //$( "#result-search-add-freinds" ).html( msg );
				$('document').remove('.my-result-list');
				$('#result-search-add-freinds').append("<ul class=\"list-group my-result-list\">");
				
				$.each(msg, function (idx, value) {
					
					var toAppend = '<li \"list-group-item\"><a class=\"list-group-item\"';
					toAppend += 'href=\"/main/showProfile.htm?username=';
					toAppend += value + '\">';
					toAppend += value;
					toAppend += '</a>';
					toAppend += '<a class=\"btn list-group-item send-request-button\"';
					toAppend += 'href=\"#" role=\"button">';
					toAppend += 'Send Friend Request';
					toAppend += '</a>';
					toAppend += '</li>';
					$('#result-search-add-freinds').append(toAppend);
					
				});
				
				$('#result-search-add-freinds').append("</ul>");
				
			});
	}
}  

/*
$('#link-show-my-friend').click(function() {
	alert("link clicked");
})
*/


$(document).on('click', '.send-request-button', function () {
	
	var $prevSibling = $(this).prev(); 
	var $username  =$prevSibling.text();
	alert($username);
	/*
	var requestAddFriend = $.ajax({
		  url: "addNewFreind.htm",
		  method: "POST",
		  data: {friendUserName:$username},
		  dataType: "json"
		});
	
	requestAddFriend.done(function(data) {
		$("#error-text-new-friend").css("display", "block");
		$('#error-text-new-friend').html(data);
	});
	*/
	
	$.ajax({
        type: "POST",
        url: "addNewFreind.htm?friendUserName="+$username,
        success: function (dataServer) {
        	$(".error-new-friend").css("display", "block");
        	$('#error-text-new-friend').html(dataServer);
        	$('#panel-add-friends').collapse()
    	},
    	error: function(err){
    		$(".error-new-friend").css("display", "block");
    		$('#error-text-new-friend').html(err);
    	}
    });
   
});
	