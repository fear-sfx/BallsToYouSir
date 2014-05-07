$(document)
		.ready(
				function() {
					var webSocket = new WebSocket(
							"ws://172.16.18.67:8080/ChatTheSeventh/chatServer");

					webSocket.onmessage = function(message) {

						var json = message.data;

						var parsedJson = $.parseJSON(json);

						if (parsedJson.message != undefined) {
							if (parsedJson.recipient == "all") {
								var newMsg = parsedJson.sender + ": "
										+ parsedJson.message + "\n";
								document.getElementById('messages').innerHTML += newMsg;
							} else {
									if( $("#"+parsedJson.sender).is(":visible") ) {

									} else {
										tooglePopup(parsedJson.sender);
									}

									var newMsg = parsedJson.sender + ": "
											+ parsedJson.message + "\n";
									document.getElementById("text"+parsedJson.sender).innerHTML += newMsg;
							}
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
						json.sender = readCookie("username");
						webSocket.send(JSON.stringify(json));
						document.getElementById("userMessage").value = "";
						return false;
					}

					function postPrivateMessage(to) {
						var json = {};
						var msg = document.getElementById("userPrivateMessage"+to).value;
						json.message = msg;
						json.recipient = to;
						json.sender = readCookie("username");
						webSocket.send(JSON.stringify(json));
						document.getElementById('text'+to).innerHTML += json.sender + " : " + msg + "\n";
						document.getElementById("userPrivateMessage"+to).value = "";
						return false;
					}
					
//					document.addEventListener('keydown', function(event) {
//						if (event.keyCode == 13) { // if ENTER is pressed
//							var status = $('#userMessage').is(":focus");
//							if(status == true) {
//								postMessage();
//							} else {
//								status = $('#userPrivateMessage').is(":focus");
//								if(status == true) {
//									postPrivateMessage(); 	
//								}
//							}
//						}
//					});

					$(document).on('click', '#onlineUsers li', function() {

						var name = $(this).html();
						
						if(name != readCookie("username"))
							tooglePopup(name);
					});

					function tooglePopup(title) {
						$("body").append("<div class=\"popup\" id=\""+title+"\">"
												+ "<div class=\"popupTitleBox\">"
												+ "<span style=\"float: left;\"> <label>"+title+"</label></span> <span"
												+ "style=\"float: right;\"> <a href=\"#\" id=\"closePopup"+title+"\"> Close"
												+ "</a>"
												+ "</span>"
												+ "</div>"
												+ "<div class=\"popUp\">"
												+ "<div id=\"pMain\">"
												+ "<textarea class=\"privateMessages\" id=\"text"+title+"\" disabled></textarea>"
												+ "</div>"
												+ "<div id=\"pMsg\">"
												+ "<input type=\"text\" id=\"userPrivateMessage"+title+"\" autofocus"
												+ "style=\"height: 40px; width: 340px;\"> "
												+ "<input id=\"privateMessageButton"+title+"\" type=\"button\" value=\"Send\">"
												+ "<br>" + "</div>" + "</div>"
												+ "</div>"
						);
						$("#closePopup"+title).click(function() {
							$("#"+title).hide();
						});
						$("#"+title).draggable();

						$("#privateMessageButton"+title).click( function() {
							postPrivateMessage(title);						
						});
					}
					
					function readCookie(name) {
					    var nameEQ = name + "=";
					    var ca = document.cookie.split(';');
					    for(var i=0;i < ca.length;i++) {
					        var c = ca[i];
					        while (c.charAt(0)==' ') c = c.substring(1,c.length);
					        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
					    }
					    return null;
					}

					$('#postMessageButton').click( function() {
						postMessage();						
					});
					
				});
