package com.qcap.core.config;

import java.security.Principal;
import java.util.Map;

import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.UUIDUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
    @Autowired
    private JwtProperties jwtProperties;
	
    @Autowired
    private RedisUtil redisUtil;

	/**
	 * 端点的配置
	 *
	 * @param stompEndpointRegistry
	 *            端点配置
	 * @date 2018/5/16
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		/**
		 * 开启ws端点 eg. ws://localhost:/8080/stomp withSockJs 当浏览器不支持原生WS对象 开启sockjs轮询
		 */
		stompEndpointRegistry
		.addEndpoint("/stomp")
		.setAllowedOrigins("*")
		.setHandshakeHandler(new CustomHandshakeHandler()) // Set custom handshake handler
		.withSockJS();
	}

	/**
	 * 配置应用消息
	 *
	 * @param registry
	 *            消息配置
	 * @date 2018/5/16
	 *
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 客户端发送消息以此为前缀
		registry.setApplicationDestinationPrefixes("/app");

		// 指可以订阅的地址，也就是服务器可以发送的地址
		// 认为语义化 topic群发 queue点对点
		registry.enableSimpleBroker("/topic", "/queue");
		// 点对点通信 地址前缀默认就是user
		registry.setUserDestinationPrefix("/user/");
	}
	
    @Bean
    public SocketSessionRegistry SocketSessionRegistry(){
        return new SocketSessionRegistry();
    }
    @Bean
    public STOMPConnectEventListener STOMPConnectEventListener(){
        return new STOMPConnectEventListener();
    }
	
	class StompPrincipal implements Principal {
	    String name;

	    StompPrincipal(String name) {
	        this.name = name;
	    }

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}

	 
	}
	
	class CustomHandshakeHandler extends DefaultHandshakeHandler {
	    // Custom class for storing principal
	    @Override
	    protected Principal determineUser(ServerHttpRequest request,
	                                      WebSocketHandler wsHandler,
	                                      Map<String, Object> attributes) {
	    	String userId= UUIDUtil.getUUID();
//	    	if(request instanceof ServletServerHttpRequest) {
//	    		ServletServerHttpRequest servletServerHttpRequest=(ServletServerHttpRequest)request;
//	    		HttpServletRequest req=servletServerHttpRequest.getServletRequest();
//	    		String token = req.getParameter(jwtProperties.getTokenHeader());
//		        userId = redisUtil.getUserId(token);
//	    	}
	        // Generate principal with UUID as name
	        return new StompPrincipal(userId);
	    }
	}
	
    public static MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
	
}
