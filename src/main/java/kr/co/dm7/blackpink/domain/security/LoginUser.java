package kr.co.dm7.blackpink.domain.security;

import kr.co.dm7.blackpink.domain.mongo.MongoUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class LoginUser implements UserDetails {

    @Getter
    private final MongoUser mongoUser;

    public LoginUser(MongoUser mongoUser) {
        this.mongoUser = mongoUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils
                .commaSeparatedStringToAuthorityList(
                        mongoUser.getRoles().stream()
                                .map(rn -> rn.name())
                                .collect(Collectors.joining(",")));
    }

    @Override
    public String getPassword() {
        return mongoUser.getPassword();
    }

    @Override
    public String getUsername() {
        return mongoUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}