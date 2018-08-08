package kr.co.dm7.blackpink.service.security;

import kr.co.dm7.blackpink.service.MongoUserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

/**
 * security 의 UserDetail 정보를 위한 서비스이다.
 */
@Service
public class SecurityUserDetailService implements ReactiveUserDetailsService {

    @Setter(onMethod = @__({@Autowired}))
    private MongoUserService mongoUserService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return mongoUserService.getByEmailOrNickName(username)
                .map(it -> new User(it.getId(),
                        it.getPassword(),
                        it.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.name()))
                                .collect(Collectors.toList())));
    }
}
