/**
 * 
 */

var username;
var toChatWithUserName;
$('document').ready(function() {
	
	'use strict';
	//alert("DOM Ready!");
	/*
	$('#link-search-friend').click(function() {
		$( "#form-add-friends" ).toggle( "slow" );
	});*/	
	
	//Check whether local storage is available or not
	if (typeof(Storage) !== "undefined") {
	    // Store
	    //localStorage.setItem("lastname", "Smith");
	    // Retrieve
	    //document.getElementById("localStorageHTML").innerHTML = localStorage.getItem("lastname");
	} else {
	    document.getElementById("localStorageHTML").innerHTML = "Sorry, your browser does not support Web Storage...";
	}
	
	
	//Calls to delete all divs from panels 	
	username =  $('#userNameTextFieldHidden').val();
	
	$('#panel-my-pending-friends').on('hidden.bs.collapse', function () {
		$('.pending-friends-div').remove();
	});
	
	$('#panel-my-friends').on('hidden.bs.collapse', function () {
		$('.friends-div').remove();
	});
	
	$('#panel-add-friends').on('hidden.bs.collapse', function () {
		$('.my-result-list').remove();
	});
	
	
	$('#panel-chat-window').on('hidden.bs.collapse', function () {
		$('#result-chat-paragraph > div.col-sm-7.col-sm-offset-2.col-md-8.col-md-offset-2.well.well-sm.self-message').remove();
		$('.self-message-paragraph').remove();
		$('.col-sm-7 col-sm-offset-2 col-md-8 col-md-offset-2 well well-sm sender-message').remove();
	});
	
	//Function for checking all messages
	function checkForMessges(toChatWithUserName) {
		if(undefined != toChatWithUserName){
			//alert("Checking Unread Messages from: "+toChatWithUserName);
			if(sessionStorage.getItem(toChatWithUserName) != null){
				var message = sessionStorage.getItem(toChatWithUserName);
				sessionStorage.removeItem(toChatWithUserName); 
				//alert(message);
				$('#result-chat-paragraph').append("<div class=\"col-sm-7 col-sm-offset-2 col-md-8 col-md-offset-2 well well-sm sender-message\"><p class=\"self-message-paragraph\">"+message+"</p></div>");
			}
		}
	}
	
	
//	//AJAX call for loading online friends
//	setInterval(function() {
//		//alert("loading friends")
//	},5000);
	
	//Load Online Freinds
	$('#load-friends-link').click(function() {
		$('.my-online-friends-result').remove();
		$('.my-online-friends-result-item').remove();
		$.ajax({
	        type: "POST",
	        url: "getOnlineFreinds.htm",
	        dataType:"json",
	        success: function (dataServer) {
	        	//alert(dataServer);
	        	//var objectJSON = JSON.parse(dataServer);
	        	$('.insert-new-ul').append("<ul class=\"nav nav-sidebar my-online-friends-result\">");
	        	$.each(dataServer, function (idx, value) {
					//alert(value);
	        		var toAppend = "<li class=\"my-online-friends-result-item\"><a data-toggle=\"collapse\" class=\"chat-with-friend\" href=\"#panel-chat-window\">"+value+"</a></li>";
	        		$('.insert-new-ul').append(toAppend);
				});
	        	$('.insert-new-ul').append("</ul>");
	    	},
	    	error: function(err){
	    		alert(err);
	    	}
	    });
		
	});
	
	//Function for appending data to session storage
	function appendToStorage(name, data){
	    var old = sessionStorage.getItem(name);
	    if(old === null) old = "";
	    sessionStorage.setItem(name, old + data);
	}
	
	
	
	//Load Unread Messages to the SessionStorage of Browser
	$('#load-unread-messages-link').click(function() {
		//alert("loading unread messages");
		$('.my-unread-messages-result').remove();
		$('.my-unread-messages-result-item').remove();
		$.ajax({
	        type: "POST",
	        url: "receiveMessage.htm",
	        data: {username: username},
	        dataType:"json",
	        success: function (dataServer) {
	        	if(dataServer != null){
	        		$('.insert-another-ul').append("<ul class=\"nav nav-sidebar my-online-friends-result\">");
	        		//$('.insert-another-ul').append("<li class=\"my-unread-messages-result-item\">Unread Messages</li>");
	        		$.each(dataServer, function (idx, value) {
						//alert(value.sentFrom);
						//alert(value.message);
						//appendToStorage(value.setFrom, value.message);
						//document.getElementById("localStorageHTML").innerHTML = sessionStorage.getItem(value.setFrom);
	        			if(sessionStorage.getItem(value.sentFrom)==null){
	        				sessionStorage.setItem(value.sentFrom, value.message);
	        				var toAppend = "<li class=\"my-unread-messages-result-item\"><a data-toggle=\"collapse\" class=\"chat-with-friend\" href=\"#panel-chat-window\">"+value.sentFrom+"</a></li>";
	    	        		$('.insert-another-ul').append(toAppend);
	        			}
	        			else{
	        				var oldData = sessionStorage.getItem(value.sentFrom);
	        				sessionStorage.setItem(value.sentFrom, oldData+ value.message);
	        			}
					});
	        		$('.insert-another-ul').append("</ul>")
	        	}
	    	},
	    	error: function(err){
	    		alert(err);
	    	}
	    });
		//document.getElementById("localStorageHTML").innerHTML = sessionStorage.getItem('sanket007');
	});
	
	
	//Automatically Loading unread messages from server
	setInterval(function() {
		$('.my-unread-messages-result').remove();
		$('.my-unread-messages-result-item').remove();
		$.ajax({
	        type: "POST",
	        url: "receiveMessage.htm",
	        data: {username: username},
	        dataType:"json",
	        success: function (dataServer) {
	        	if(dataServer != null){
	        		$('.insert-another-ul').append("<ul class=\"nav nav-sidebar my-online-friends-result\">");
	        		//$('.insert-another-ul').append("<li class=\"my-unread-messages-result-item\">Unread Messages</li>");
	        		$.each(dataServer, function (idx, value) {
						//alert(value.sentFrom);
						//alert(value.message);
						//appendToStorage(value.setFrom, value.message);
						//document.getElementById("localStorageHTML").innerHTML = sessionStorage.getItem(value.setFrom);
	        			if(sessionStorage.getItem(value.sentFrom)==null){
	        				sessionStorage.setItem(value.sentFrom, value.message);
	        				var toAppend = "<li class=\"my-unread-messages-result-item\"><a data-toggle=\"collapse\" class=\"chat-with-friend\" href=\"#panel-chat-window\">"+value.sentFrom+"</a></li>";
	    	        		$('.insert-another-ul').append(toAppend);
	        			}
	        			else{
	        				var oldData = sessionStorage.getItem(value.sentFrom);
	        				sessionStorage.setItem(value.sentFrom, oldData+ value.message);
	        			}
					});
	        		$('.insert-another-ul').append("</ul>")
	        	}
	    	},
	    	error: function(err){
	    		//alert(err);
	    	}
	    });
	},2000);

	//Automatically Load Unread messages
	setInterval(function() {
	      // Do something every 5 seconds
		checkForMessges(toChatWithUserName);
	}, 2000);
	
	//Ajax call for loading list of friends
	$('#panel-my-friends').on('show.bs.collapse', function () {
		 var $username = $('#userNameTextFieldHidden').val();
		 username = $('#userNameTextFieldHidden').val();
		 //alert($username);
		 var request = $.ajax({
			  url: "showFriendList.htm",
			  method: "GET",
			  data: {username:$username},
			  dataType: "json"
			});
			 
			request.done(function( msg ) {
				//alert(msg.firstName);
				try{
					//var jsonObj = JSON.parse(msg);
					//var ctx = <%=request.getContextPath()%>;
					var $ctx = $('#servletContextValueHidden').val();
					$.each(msg, function (idx, value) {
						if(value.firstName!='NULL'){
							var toAppend = "<div class=\"col-xs-6 col-sm-3 placeholder friends-div\">";
							if(value.profilePictureName!=null){
								//alert("iamge is not null");
								toAppend +="<img src="+$ctx+"/"+value.profilePictureName+" width=\"200\" height=\"200\"   />";
							}
							else{
								toAppend +="<img src=\"data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" +
								"\"width=\"200\" " +
								"height=\"200\" " +
								"class=\"img-responsive\"" +
								"alt=\"Generic placeholder thumbnail\">";
							}
							
							toAppend+="<h4>"+value.firstName+" "+ value.lastName+ "</h4>";
							toAppend+="<span class=\"text-muted\">Send Message</span>";
							$('#my-friends-results-div').append(toAppend);
							$('#my-friends-results-div').append("</div>");
						}
						else{
							$('#my-friends-results-div').html("Currently you have no friends!");
						}
						
					});
				}
				catch(e){
					$('#my-friends-results-div').html("Currently you have no friends!");
				}
				
				
			});
			
			request.error(function(error) {
				alert(error);
			});
	});
	
	//Send Message AJAX Call
	$('#chat-send-btn').click(function(ev) {
		//alert("Send button clicked");
		//alert(toChatWithUserName);
		var message = $('#chat-text-input').val();
		
		$.ajax({
	        type: "POST",
	        url: "sendMessage.htm",
	        data: {user: username, message: message, friendUsername:toChatWithUserName },
	        dataType: "json"
	    })
	            .done(function (data) {
	                
	            }).error(function (err) {
	        //alert(err);
	    });
		$('#result-chat-paragraph').append("<div class=\"col-sm-7 col-sm-offset-2 col-md-8 col-md-offset-2 well well-sm self-message\"><p class=\"self-message-paragraph\">"+message+"</p></div>");
		//alert("Now calling checkMessgae Function");
		//checkForMessges(toChatWithUserName);
		ev.preventDefault();
	});
	
	//Ajax call for loading list of friends
	$('#panel-my-pending-friends').on('show.bs.collapse', function () {
		 var $username = $('#userNameTextFieldHidden').val();
		 //alert($username);
		 var request = $.ajax({
			  url: "showPendingFriendList.htm",
			  method: "GET",
			  data: {username:$username},
			  dataType: "json"
			});
			 
			request.done(function( msg ) {
				//alert(msg.firstName);
				try{
					//var jsonObj = JSON.parse(msg);
					if(msg.length==0){
						$('#my-pending-friends-results-div').html("Currently you have no pending requests!");
					}
					else{
						var $ctx = $('#servletContextValueHidden').val();
						$.each(msg, function (idx, value) {
							
							if(value.firstName!= 'NULL'){
								var toAppend = "<div class=\"col-xs-6 col-sm-3 placeholder pending-friends-div\">";
								if(value.profilePictureName!=null){
									//alert("iamge is not null");
									toAppend +="<img src="+$ctx+"/"+value.profilePictureName+" width=\"200\" height=\"200\"   />";
								}
								else{
									toAppend +="<img src=\"data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" +
									"\"width=\"200\" " +
									"height=\"200\" " +
									"class=\"img-responsive\"" +
									"alt=\"Generic placeholder thumbnail\">";
								}
								toAppend+="<h4>"+value.firstName+" "+ value.lastName+ "</h4>";
								toAppend+="<span class=\"hidden\">"+value.username+ "</span>";
								toAppend+="<span class=\"text-muted\"><a class=\"link-to-accept-friend\">Accpet</a></span>";
								toAppend+="<span class=\"text-muted\">&nbsp;/&nbsp;</span>";
								toAppend+="<span class=\"text-muted\"><a class=\"link-to-reject-friend\">Reject</a></span>";
								$('#my-pending-friends-results-div').append(toAppend);
								$('#my-pending-friends-results-div').append("</div>");
							}
							else{
								$('#my-pending-friends-results-div').html("Currently you have no friends!");
							}
						});
					}
				}
				catch(e){
					$('#my-pending-friends-results-div').html("Currently you have no friends!");
				}	
			});
			
			request.error(function(error) {
				alert(error);
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

//When user selects some one to chat with
$(document).on('click','.chat-with-friend',function(){
	var $aText = $(this).text();
	//alert($aText);
	toChatWithUserName = $aText;	
	var element = document.getElementById("panel-heading-of-chat-window");
	element.innerHTML = "Chat with "+toChatWithUserName;
});

//$(document).on('click','#chat-send-btn',function(){
//	var message = $('#chat-text-input').val();
//	$.ajax({
//        type: "POST",
//        url: "sendMessage.htm",
//        data: {user: username, message: message, friendUsername:toChatWithUserName },
//        dataType: "json"
//    })
//            .done(function (data) {
//                
//            }).error(function (err) {
//        alert(err);
//    });
//});



$(document).on('click', '.send-request-button', function () {
	
	var $prevSibling = $(this).prev(); 
	var $username  =$prevSibling.text();
	//alert($username);
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

//Link to accept friend
$(document).on('click', '.link-to-accept-friend', function () {
	var $span = $(this).parent().prev();
	var $username = $span.text();
	//alert($username);
	
	$.ajax({
        type: "POST",
        url: "acceptFriend.htm?friendUserName="+$username,
        success: function (dataServer) {
        	$(".error-accept-new-friend").css("display", "block");
        	$('#error-text-accept-new-friend').html(dataServer);
        	$('#panel-my-pending-friends').collapse('hide');
			$('.pending-friends-div').remove();
        	$('#panel-my-friends').collapse()
    	},
    	error: function(err){
    		$(".error-accept-new-friend").css("display", "block");
    		$('#error-text-accept-new-friend').html(err);
    	}
    });
});
	