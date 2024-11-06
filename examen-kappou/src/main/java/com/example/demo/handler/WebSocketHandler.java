package com.example.demo.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class WebSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();
    private List<TextMessage> mensajes = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Cuando se establece una conexión, agregar la sesión a la lista
        sessions.add(session);
        // Enviar todos los mensajes anteriores al nuevo cliente
        for (TextMessage mensaje : mensajes) {
            session.sendMessage(new TextMessage(mensaje.getPayload()));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        
        // Si el mensaje es "websocket", responde solo a ese cliente
        if (payload == "websocket") {
            String responseText = "WebSocket es un protocolo de comunicación bidireccional. WebSocket trabaja en el puerto 80";
            session.sendMessage(new TextMessage(responseText));
        } else {
            // Guardar el mensaje en la lista de mensajes
            mensajes.add(message);

            // Enviar el mensaje a todos los clientes conectados
            for (WebSocketSession webSocketSession : sessions) {
                if (webSocketSession.isOpen()) {
                    webSocketSession.sendMessage(new TextMessage(payload));
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status)
            throws Exception {
        // Eliminar la sesión cuando se cierra la conexión
        sessions.remove(session);
    }
}