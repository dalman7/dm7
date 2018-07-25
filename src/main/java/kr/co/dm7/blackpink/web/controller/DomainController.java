package kr.co.dm7.blackpink.web.controller;

import kr.co.dm7.blackpink.domain.mongo.Domain;
import kr.co.dm7.blackpink.repository.mongo.DomainRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 일반적인 방식의 controller 를 작성한다.
 * 이것과 비교해서 Reactive 방식은 샘플로 따로 작성할 예정이다.
 * Reactive 방식을 꼭 써야 할까? 생각도 되지만 Spring Boot 2 를 쓴다는 것. 그리고 Spring 5 를 쓴다는 것 자체가
 * Spring 에서 지원하는 WebFlux 를 사용하겠다는 의미이기 때문에 이렇게 하는 것이 중장기적으로는 맞다고 판단한다.
 * 여튼... 그것도 뭐 잘 성공해야 써먹는거지 ㅋ
 *
 * @author doubleseven
 * @version 1.0
 */
@RestController
public class DomainController {

    @Setter(onMethod = @__({@Autowired}))
    private DomainRepository domainRepository;

    /**
     * Domain 정보 한건을 얻는다
     *
     * @param id
     * @return
     */
    @GetMapping("/domain/{id}")
    public Mono<Domain> getDomain(@PathVariable String id) {
        return domainRepository.findById(id);
    }

    /**
     * Domain 전체 정보를 얻는다.
     *
     * @return
     */
    @GetMapping("/domains")
    public Flux<Domain> getDomain() {
        return domainRepository.findAll();
    }
}
