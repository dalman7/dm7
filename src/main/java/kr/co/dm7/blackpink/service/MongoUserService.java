package kr.co.dm7.blackpink.service;

import kr.co.dm7.blackpink.domain.mongo.MongoUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MongoUserService {
    /**
     * MongoUser 정보를 얻는다.
     *
     * @param id
     * @return
     */
    Mono<MongoUser> getMongoUser(String id);

    /**
     * MongoUser 의 모든 정보를 얻는다.
     *
     * @return
     */
    Flux<MongoUser> getAll();

    /**
     * MongoUser 정보를 세팅한다.
     *
     * @param mongoUser
     */
    Mono<MongoUser> setMongoUser(MongoUser mongoUser);

    /**
     * MongoUser 정보를 삭제한다.
     *
     * @param id
     */
    void removeMongoUser(String id);

    /**
     * email 정보로 MongoUser 정보를 가져온다.
     *
     * @param email
     * @return
     */
    Mono<MongoUser> getByEmail(String email);

    /**
     * 사용자의 unique 한 저옵를 가지고 MongoUser 정보를 가져온다.
     *
     * @param userUnique
     * @return
     */
    Mono<MongoUser> getByEmailOrNickName(String userUnique);
}
