package kr.co.dm7.blackpink.web.controller;

import kr.co.dm7.blackpink.domain.mongo.MongoUser;
import kr.co.dm7.blackpink.domain.security.LoginUser;
import kr.co.dm7.blackpink.service.MongoUserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;


@RestController
@Slf4j
@PreAuthorize("hasRole('ADMIN')")
public class MongoUserController {

    @Setter(onMethod = @__({@Autowired}))
    private MongoUserService mongoUserService;

    private static final String PATH_VARIABLE_ID = "id";
    private static final String PATH_ID = "{" + PATH_VARIABLE_ID + "}";

    /**
     * 전체 유저 정보를 가져온다.
     *
     * @return
     */
    @GetMapping("/mongoUsers")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    public Flux<MongoUser> getAllMongoUsers(@AuthenticationPrincipal LoginUser loginUser) {
        logLoginUserInfomations(loginUser);
        return mongoUserService.getAll();
    }

    /**
     * 전체 유저 정보를 등록 수정한다.
     *
     * @param id
     * @return
     */
    @GetMapping("/mongoUser/" + PATH_ID)
    public Mono<ResponseEntity<MongoUser>> getMongoUserById(@PathVariable(PATH_VARIABLE_ID) String id, @AuthenticationPrincipal LoginUser loginUser) {
        logLoginUserInfomations(loginUser);
        return mongoUserService.getMongoUser(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * 특정 유저 정보를 삭제한다.
     *
     * @param mongoUserMono
     * @return
     */
    @PostMapping("/mongoUser")
    public Mono<ResponseEntity<MongoUser>> createMongoUser(@Validated @RequestBody MongoUser mongoUserMono, @AuthenticationPrincipal LoginUser loginUser) {
        logLoginUserInfomations(loginUser);
        return mongoUserService.setMongoUser(mongoUserMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    void logLoginUserInfomations(LoginUser loginUser) {
        MongoUser myUser = loginUser.getMongoUser();
        log.info("################################ UserInfo Start ################################");
        log.info("id", myUser.getId());
        log.info("password : {}", myUser.getPassword());
        log.info("email : {}", myUser.getEmail());
        log.info("lastName : {}", myUser.getLastName());
        log.info("firstName : {}", myUser.getFirstName());
        log.info("role : {}", myUser.getRoles().stream().map(role -> role.name()).collect(Collectors.joining(",")));
        log.info("################################ UserInfo End ################################");
    }

}
