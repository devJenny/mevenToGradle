package com.crud.home.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                .setAllowedOriginPatterns("http://localhost:9090")
                .withSockJS(); // STOMP over WebSocket 엔드포인트를 등록
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // /topic으로 시작하는 주소로 메시지를 클라이언트에게 전달하는 간단한 메모리 기반 브로커를 활성화
        config.setApplicationDestinationPrefixes("/app"); // @MessageMapping으로 지정된 메서드가 기본적으로 바인딩되는 /app에 메시지 브로커를 설정
    }

}
