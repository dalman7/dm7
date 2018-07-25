package kr.co.dm7.blackpink;

import kr.co.dm7.blackpink.domain.User;
import kr.co.dm7.blackpink.repository.map.UserMapRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 서버가 시작될때 초기에 세팅해야 할 정보들을 반영하기 위한 Runner 로써
 * 이 역시도 실제 구현 코드에서는 제거될 예정이다.
 *
 * @author doubleseven
 * @version 1.0
 */
@Component
@Order(1)
public class InitMemoryData implements ApplicationRunner {

    @Setter(onMethod = @__({@Autowired}))
    private UserMapRepository userMapRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setMemoryUsers();
    }

    void setMemoryUsers() {
        Arrays.asList(
                User.builder().id("doubleseven").name("더블세븐").build(),
                User.builder().id("apetoy94").name("만동").build(),
                User.builder().id("caleb940").name("종달").build()
        ).stream().forEach(
                it -> userMapRepository.setOne(it)
        );
    }
}
