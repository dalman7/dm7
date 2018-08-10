package kr.co.dm7.blackpink.service.security;

import kr.co.dm7.blackpink.domain.security.LoginUser;
import kr.co.dm7.blackpink.service.MongoUserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * security 의 UserDetail 정보를 위한 서비스이다.
 */
@Service
public class SecurityUserDetailService implements ReactiveUserDetailsService {

    @Setter(onMethod = @__({@Autowired}))
    private MongoUserService mongoUserService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        Mono<UserDetails> userDetails = mongoUserService.getByEmailOrNickName(username)
                .map(LoginUser::new);
        System.out.println(userDetails.toString());
        return userDetails;
    }
}
