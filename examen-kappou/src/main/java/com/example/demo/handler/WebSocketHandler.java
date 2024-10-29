package com.example.demo.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class WebSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();
    private List<String> mensajes = new ArrayList<String>();
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Cuando se establece una conexión, agregar la sesión a la lista
        sessions.add(session);
        //foreach(){
        	//cosas
        //}
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Cuando se recibe un mensaje, enviarlo a todos los clientes conectados
    	mensajes.add(message.getPayload());
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        // Eliminar la sesión cuando se cierra la conexión
        sessions.remove(session);
    }
}


