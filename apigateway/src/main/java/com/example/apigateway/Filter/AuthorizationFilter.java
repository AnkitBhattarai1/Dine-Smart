package com.example.apigateway.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;

public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    public static class Config {
        private String role;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }

    ServerWebExchange a;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            String role = exchange.getRequest().getHeaders().getFirst("role");

            if (role == null || !role.equals(config.getRole())) {
                throw new RuntimeException("Unauthorized");
            }
            return chain.filter(exchange);
        };
    }
}
