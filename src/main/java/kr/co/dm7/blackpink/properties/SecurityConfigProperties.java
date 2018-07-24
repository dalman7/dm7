package kr.co.dm7.blackpink.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "oauth2.social")
public class SecurityConfigProperties {
    private List<String> clients;

    private List<OAuth2ProviderName> schemaByVersion;

    enum OAuth2ProviderName {
        FACEBOOK,
        GOOGLE,
        NAVER
    }
}
