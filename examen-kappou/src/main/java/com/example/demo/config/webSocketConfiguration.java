
package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.example.demo.handler.WebSocketHandler;

@Configuration
@EnableWebSocket
public class webSocketConfiguration implements WebSocketConfigurer {

	private WebSocketHandler ChatWebSocketHandler = new WebSocketHandler();
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Registra el manejador de WebSocket
        registry.addHandler(ChatWebSocketHandler, "/chat"); // Permitir cualquier origen (modificar según seguridad)
    }
}



