package kr.co.dm7.blackpink.web.controller;

import kr.co.dm7.blackpink.domain.mongo.MongoUser;
import kr.co.dm7.blackpink.service.MongoUserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
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
    public Flux<MongoUser> getAllMongoUsers() {
        return mongoUserService.getAll();
    }

    /**
     * 전체 유저 정보를 등록 수정한다.
     *
     * @param id
     * @return
     */
    @GetMapping("/mongoUser/" + PATH_ID)
    public Mono<ResponseEntity<MongoUser>> getMongoUserById(@PathVariable(PATH_VARIABLE_ID) String id) {
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
    public Mono<ResponseEntity<MongoUser>> createMongoUser(@Validated @RequestBody MongoUser mongoUserMono) {
        return mongoUserService.setMongoUser(mongoUserMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
