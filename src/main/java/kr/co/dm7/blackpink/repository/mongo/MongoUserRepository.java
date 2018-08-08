package kr.co.dm7.blackpink.repository.mongo;

import kr.co.dm7.blackpink.domain.mongo.MongoUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MongoUserRepository extends ReactiveMongoRepository<MongoUser, String> {

    Mono<MongoUser> findMongoUserByEmail(String email);

    Mono<MongoUser> findMongoUserByEmailOrNickName(String email, String nickName);
}