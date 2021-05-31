package com.inet.code.entity.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
public class JwtToken implements AuthenticationToken {
    /**
     * 密钥
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
