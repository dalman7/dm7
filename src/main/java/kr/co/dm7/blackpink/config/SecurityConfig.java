package kr.co.dm7.blackpink.config;

import kr.co.dm7.blackpink.properties.SecurityConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Oauth가 적용된 Security 를 위한 Config
 * 해당 소스를 제작하기 위해서 참고한 URL
 * - http://www.baeldung.com/spring-security-5-oauth2-login
 *
 * @author doubleseven
 * @version 1.0
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityConfigProperties securityConfigProperties;


    /**
     * 인증 관련해서 ignore 처리할 것이 있으면 이것을 통해서 처리해주면 된다.
     * 주로 리소스나 뭐 그런것에 대해서 ignore 해줄때 쓰면 될 것 같다.
     *
     * @param web
     * @throws Exception
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**")
                .antMatchers("/script/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**")
                .antMatchers("/img/**")
                .antMatchers("/console/**")
                .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/oauth2_login").permitAll()
                    .antMatchers("/**").permitAll()
        // oauth2
        .and()
                .oauth2Login() // auth2 로그
                    .loginPage("/oauth2/login.html")
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .authorizedClientService(authorizedClientService())
                    .defaultSuccessUrl("/oauth2/success") // 성공시
                    .failureUrl("/oauth2/fail") // 실패시
        // form Login
        .and()
                .formLogin()
                    .loginPage("/form/login.html")
                    .defaultSuccessUrl("/my")

        // logout
        .and()
                .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/post/list")
        ;

    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {

        List<ClientRegistration> registrations = securityConfigProperties.getClients().stream()
                .map(client -> getRegistration(client))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    ClientRegistration getRegistration(String client) {
//        String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-id");
//
//        if (clientId == null) {
//            return null;
//        }
//
//        String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-secret");

        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        } else if (client.equals("facebook")) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        }
        return null;
    }
}
