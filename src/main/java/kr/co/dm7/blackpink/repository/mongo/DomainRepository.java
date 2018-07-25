package kr.co.dm7.blackpink.repository.mongo;

import kr.co.dm7.blackpink.domain.mongo.Domain;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Domain 데이터를 가져오기 위한 Repository
 */
@Repository
public interface DomainRepository extends ReactiveMongoRepository<Domain, String>, DomainRepositoryCustom {

    Mono<Domain> findFirstByName(String name);

    Mono<Domain> findByNameAndDisplay(String name, boolean display);

    Flux<Domain> findByUrl(String url);

    //Mongo JSON query
    @Query("{name:'?0'}")
    Mono<Domain> findCustomByName(String name);

    @Query("{name: { $regex: ?0 } })")
    Flux<Domain> findCustomByRegExDomain(String name);
}
