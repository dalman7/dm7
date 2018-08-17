package kr.co.dm7.blackpink.config;

import kr.co.dm7.blackpink.domain.enums.Role;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

import java.net.URI;

/**
 * webflux 기반의 security 설정을 위해서 세팅한다.
 */
@EnableWebFluxSecurity
// @EnableReactiveMethodSecurity
public class WebFluxSecurityConfig {

//    /**
//     * path 와 method 에 대한 security 를 설정한다.
//     *
//     * @param http
//     * @return
//     */
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        return http
//                .csrf().disable()
//                .authorizeExchange()
//                .matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .pathMatchers("/", "userinfo").permitAll()
//                // user
//                .pathMatchers(HttpMethod.GET, "/user").permitAll()
//                .pathMatchers(HttpMethod.PUT, "/user").hasRole(Role.MANAGER.name())
//                .pathMatchers(HttpMethod.DELETE, "/user").hasRole(Role.MANAGER.name())
//
//                // domains
//                .pathMatchers(HttpMethod.GET, "/domains").hasRole(Role.MANAGER.name())
//
//                .anyExchange().authenticated()
//                .anyExchange().permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin()
//                .and()
////                .logout()
////                .logoutSuccessHandler(logoutSuccessHandler())
////                .and()
//                .build();
//    }

//    /**
//     * 로그아웃을 위한 핸들러
//     *
//     * @return
//     */
//    @Bean
//    public ServerLogoutSuccessHandler logoutSuccessHandler() {
//        RedirectServerLogoutSuccessHandler logoutSuccessHandler = new RedirectServerLogoutSuccessHandler();
//        logoutSuccessHandler.setLogoutSuccessUrl(URI.create("/users"));
//        return logoutSuccessHandler;
//    }

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
