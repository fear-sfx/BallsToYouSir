package com.cts.socket.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.cts.utils.Message;
import com.cts.utils.Room;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ServerEndpoint("/chatServer")
public class WebsocketEndpoint extends HttpServlet {

	private static final long serialVersionUID = 7162337036688208899L;

	private static List<Session> clients = Collections
			.synchronizedList(new ArrayList<Session>());

	@OnOpen
	public void onOpen(Session session) throws IOException {
		System.out.println("new user connected..");
		clients.add(session);

		Gson gson = new GsonBuilder().create();
		String json = "{\"onlineUsers\": ";
		json += gson.toJson(Room.clients) + "}";

		synchronized (clients) {
			for (Session client : clients) {
				client.getBasicRemote().sendText(json);
			}
		}
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		System.out.println("user dropped out..");
		int id = clients.indexOf(session);
		Room.clients.remove(id);
		clients.remove(session);

		Gson gson = new GsonBuilder().create();
		String json = "{\"onlineUsers\": ";
		json += gson.toJson(Room.clients) + "}";

		synchronized (clients) {
			for (Session client : clients) {
				client.getBasicRemote().sendText(json);
			}
		}
	}

	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println(message);
		Gson gson = new GsonBuilder().create();

		Message msg = gson.fromJson(message, Message.class);

		int id = 0;
		for (; id < clients.size(); id++)
			if (clients.get(id).equals(session))
				break;
		msg.setSender(Room.clients.get(id));

		if (msg.getRecipient().equals("all")) {
			String json = gson.toJson(msg);

			synchronized (clients) {
				for (Session client : clients) {
					client.getBasicRemote().sendText(json);
				}
			}
		} else {
			String json = gson.toJson(msg);

			synchronized (clients) {
				for (Session client : clients) {
					client.getBasicRemote().sendText(json);
				}
			}			
		}
	}

}
