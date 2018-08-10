package kr.co.dm7.blackpink;

import kr.co.dm7.blackpink.domain.enums.Role;
import kr.co.dm7.blackpink.domain.mongo.Address;
import kr.co.dm7.blackpink.domain.mongo.Domain;
import kr.co.dm7.blackpink.domain.mongo.MongoUser;
import kr.co.dm7.blackpink.repository.mongo.DomainRepository;
import kr.co.dm7.blackpink.repository.mongo.MongoUserRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MongoDB 에 반영될 초기화 데이터로써
 * 해당 정보 역시 테스트가 종료되고 필요하다고 판단되는 순간 Mongodb 세팅을 하게 되면 그때는
 * 제거될 예정이다.
 *
 * @author doubleseven
 * @version 1.0
 */
@Component
@Order(2)
@Slf4j
public class InitMongoDB implements ApplicationRunner {

    @Setter(onMethod = @__({@Autowired}))
    private DomainRepository domainRepository;

    @Setter(onMethod = @__({@Autowired}))
    private MongoUserRepository mongoUserRepository;

    @Setter(onMethod = @__({@Autowired}))
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        initDomains();
        initMongoUsers();
    }

    void initMongoUsers() {

        mongoUserRepository.saveAll(
                Arrays.asList(
                        MongoUser.builder()
                                .email("caleb940@naver.com")
                                .nickName("caleb940")
                                .firstName("종훈")
                                .lastName("박")
                                .password(passwordEncoder.encode("caleb940_pwd"))
                                .roles(Arrays.asList(Role.ADMIN, Role.MANAGER))
                                .build(),
                        MongoUser.builder()
                                .email("apetoy94@naver.com")
                                .nickName("apetoy94")
                                .firstName("동민")
                                .lastName("장")
                                .password(passwordEncoder.encode("apetoy94_pwd"))
                                .roles(Collections.singletonList(Role.MANAGER))
                                .build(),
                        MongoUser.builder()
                                .email("doubleseven@naver.com")
                                .nickName("doubleseven")
                                .firstName("현선")
                                .lastName("임")
                                .password(passwordEncoder.encode("doubleseven_pwd"))
                                .roles(Collections.singletonList(Role.USER))
                                .build()
                )
        ).subscribe();

    }

    void initDomains() {
        List<Domain> domains = Arrays.asList(
                Domain.builder()
                        .name("만동홈페이지")
                        .display(Boolean.TRUE)
                        .url("http://만동만동.com")
                        .address(Address.builder()
                                .firstAddress("인천시 뭐구 뭐동")
                                .secondAddress("뭐뭐 아파트 몇동 몇호")
                                .postCode("123-456")
                                .build())
                        .build(),
                Domain.builder()
                        .name("종다리우리집")
                        .display(Boolean.TRUE)
                        .url("http://종다리.com")
                        .address(Address.builder()
                                .firstAddress("경기도 김포시 뭐동")
                                .secondAddress("머시기 아파트 가동 나호")
                                .postCode("789-111")
                                .build())
                        .build(),
                Domain.builder()
                        .name("칠칠월드")
                        .display(Boolean.TRUE)
                        .url("http://더블세븐.com")
                        .address(Address.builder()
                                .firstAddress("경기도 용인시 수지구")
                                .secondAddress("정말로 아파트 AA동 111호")
                                .postCode("998-778")
                                .build())
                        .build()
        );
        domainRepository.saveAll(domains).subscribe();
    }
}
