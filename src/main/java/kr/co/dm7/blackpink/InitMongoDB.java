package kr.co.dm7.blackpink;

import kr.co.dm7.blackpink.domain.mongo.Address;
import kr.co.dm7.blackpink.domain.mongo.Domain;
import kr.co.dm7.blackpink.repository.mongo.DomainRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {

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
