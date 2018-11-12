package com.mfr.usersManager.Config;

import com.mfr.usersManager.DTO.*;
import com.mfr.usersManager.Util.PasswordUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public PasswordUtil passwordUtil() {
        return new PasswordUtil();
    }

    @Bean
    RegisterResponseDTO registerResponseDTO() {
        return new RegisterResponseDTO();
    }

    @Bean
    RegisterRequestDTO registerRequestDTO() {
        return new RegisterRequestDTO();
    }

    @Bean
    LoginRequestDTO loginRequestDTO() {
        return new LoginRequestDTO();
    }

    @Bean
    LoginResponseDTO loginResponseDTO() {
        return new LoginResponseDTO();
    }

    @Bean
    PingDTO pingDTO() {
        return new PingDTO();
    }

}
