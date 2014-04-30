$(document)
		.ready(
				function() {
					var webSocket = new WebSocket(
							"ws://localhost:8080/ChatTheSeventh/chatServer");

					webSocket.onmessage = function(message) {

						var json = message.data;

						var parsedJson = $.parseJSON(json);

						if (parsedJson.message != undefined) {
							var newMsg = parsedJson.sender + ": " + parsedJson.message + "\n";
							document.getElementById('messages').innerHTML += newMsg;
						}
						if (parsedJson.onlineUsers != undefined) {
							document.getElementById('onlineUsers').innerHTML = "";
							for (var i = 0; i < parsedJson.onlineUsers.length; i++) {
								document.getElementById('onlineUsers').innerHTML += "<li>"
										+ parsedJson.onlineUsers[i] + "</li>";
							}
						}
					};

					function postMessage() {
						var json = {};
						var msg = document.getElementById("userMessage").value;
						json.message = msg;
						json.recipient = "all";
						json.sender = "";
						webSocket.send(JSON.stringify(json));
						document.getElementById("userMessage").value = "";
						return false;
					}

					document.addEventListener('keydown', function(event) {
						if (event.keyCode == 13) { // if ENTER is pressed
							postMessage();
						}
					});
					
				});

$(document).on('çlick', '#loginBu', function() {
	alert("aaaaS");
});

$(document).on('click', '#onlineUsers li', function() {

	var name = $(this).html();
	
	$("#titlePopup").text(name);

	$(".popup").toggle();

	$("#closePopup").click(function() {
		$(".popup").hide();
	});

	$(".popup").draggable();
});