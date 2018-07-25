package kr.co.dm7.blackpink.web.controller;

import kr.co.dm7.blackpink.domain.mongo.Domain;
import kr.co.dm7.blackpink.repository.mongo.DomainRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Slf4j
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

    /**
     * 이름으로 도메인 객체 찾기
     * ex) http://localhost:8080/domain/name/만동홈페이지
     *
     * @param name
     * @return
     */
    @GetMapping("domain/name/{name}")
    public Mono<Domain> getDomainByName(@PathVariable String name) {
        return domainRepository.findFirstByName(name);
    }

    /**
     * 이름으로 도메인 객체 찾기 ( mongo json query 이용 )
     * ex) http://localhost:8080/domain/name2/만동홈페이지
     *
     * @param name
     * @return
     */
    @GetMapping("domain/name2/{name}")
    public Mono<Domain> getDomainByName2(@PathVariable String name) {
        return domainRepository.findCustomByName(name);
    }

    /**
     * 이름과 노출여부 정보로 도메인 객체 찾기
     * ex) http://localhost:8080/domain/name/만동홈페이지/true
     * ex) http://localhost:8080/domain/name/만동홈페이지/false
     *
     * @param name
     * @param display
     * @return
     */
    @GetMapping("domain/name/{name}/{display}")
    public Mono<Domain> getDomainByNameAndDisplay(@PathVariable String name, @PathVariable boolean display) {
        return domainRepository.findByNameAndDisplay(name, display);
    }

    /**
     * URL 앞 문자열을 보고 도메인 객체 정보를 찾는다
     * ex) http://localhost:8080/domain/url/http:
     *
     * @param urlStartString
     * @return
     */
    @GetMapping("domain/url/{urlStartString}")
    public Flux<Domain> getDomainByUrl(@PathVariable String urlStartString) {
        return domainRepository.findByUrlIsStartingWith(urlStartString);

    }

    /**
     * 도메인의 상태 계속 호출할때마다 바꿔준다. ( display :true <-> false )
     * ex) http://localhost:8080/domain/name/만동홈페이지/changeDisplay
     *
     * @param name
     * @return
     */
    @GetMapping("domain/name/{name}/changeDisplay")
    public Mono<Domain> getChangedDisplayDomain(@PathVariable String name) {
        domainRepository.findFirstByName(name)
                .subscribe(
                        value -> domainRepository.updateDisplay(value.getName(), !value.isDisplay()),
                        error -> error.printStackTrace(),
                        () -> log.info("completed without a value")
                );
        return domainRepository.findFirstByName(name);
    }

    /**
     * Mongodb 정규식 (regexp) 을 이용한 쿼리
     * 상세한 쿼리는 아래 URL 참고할 것
     * - https://docs.mongodb.com/manual/reference/operator/query/regex/
     *
     * @param name
     * @return
     */
    @GetMapping("domain/name/regexp/{name}")
    public Flux<Domain> getRegexp(@PathVariable String name) {
        return domainRepository.findCustomByRegExDomain(name);
    }
}
