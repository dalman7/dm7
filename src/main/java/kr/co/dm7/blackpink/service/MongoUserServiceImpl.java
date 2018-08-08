package kr.co.dm7.blackpink.service;

import kr.co.dm7.blackpink.domain.mongo.MongoUser;
import kr.co.dm7.blackpink.repository.mongo.MongoUserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MongoUserServiceImpl implements MongoUserService {

    @Setter(onMethod = @__({@Autowired}))
    private MongoUserRepository mongoUserRepository;

    @Override
    public Mono<MongoUser> getMongoUser(String id) {
        return mongoUserRepository.findById(id);
    }

    @Override
    public Flux<MongoUser> getAll() {
        return mongoUserRepository.findAll();
    }

    @Override
    public Mono<MongoUser> setMongoUser(MongoUser mongoUser) {
        return mongoUserRepository.insert(mongoUser);
    }

    @Override
    public void removeMongoUser(String id) {
        mongoUserRepository.deleteById(id);
    }

    @Override
    public Mono<MongoUser> getByEmail(String email) {
        return mongoUserRepository.findMongoUserByEmail(email);
    }

    @Override
    public Mono<MongoUser> getByEmailOrNickName(String userUnique) {
        return mongoUserRepository.findMongoUserByEmailOrNickName(userUnique, userUnique);
    }
}
