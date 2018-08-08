package kr.co.dm7.blackpink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * webflux 기반의 security 설정을 위해서 세팅한다.
 */
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    /**
     * 패스워드 및 기타 암호화가 필요한 사항들에 대해서 인코딩을 하기 위한 Bean 이다
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
