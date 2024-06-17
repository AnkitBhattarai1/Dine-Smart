package com.example.apigateway.Filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.apigateway.Utils.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    RouteValidator routeValidator;

    // @Autowired
    // RestTemplate restTemplate;
    @Autowired
    JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {

                if (!(exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))) {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                String authHeader;
                List<String> list = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);

                if (list == null) {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                authHeader = list.get(0);

                if (authHeader != null && authHeader.startsWith("Bearer "))
                    authHeader = authHeader.substring(7);

                try {
                    // restTemplate.getForEntity("http://AUTH_SERVICE/validate?token=" + authHeader,
                    // String.class);
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return chain.filter(exchange);
        });

    }
}
