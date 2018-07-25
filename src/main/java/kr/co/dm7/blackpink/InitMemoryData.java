package kr.co.dm7.blackpink;

import kr.co.dm7.blackpink.domain.User;
import kr.co.dm7.blackpink.repository.map.UserMapRepository;
import kr.co.dm7.blackpink.repository.mapper.UserMapper;
import kr.co.dm7.blackpink.repository.mapper.UserXMLMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class InitMemoryData implements ApplicationRunner {

    @Setter(onMethod = @__({@Autowired}))
    private UserMapRepository userMapRepository;

    @Setter(onMethod = @__({@Autowired}))
    private UserMapper userMapper;

    @Setter(onMethod = @__({@Autowired}))
    private UserXMLMapper userXMLMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setMemoryUsers(); // 메모리 DB 에 사용자 넣기
        // checkMybatis(); // Mybatis 매퍼 테스트
    }

//    void checkMybatis() {
//        User user = userMapper.findOne("doubleseven");
//        log.info("##################################################################################################");
//        log.info("interface Mapper 에 의한 조회 : " + user.toString());
//        log.info("##################################################################################################");
//
//        // xml mapper 에 의한 insert
//        User newUser = User.builder().id("abcd").name("더블세븐2").build();
//        userXMLMapper.insert(newUser);
//
//        // xml mapper 에 의한 update
//        newUser.setName("더블세븐2_이름바뀜");
//        userXMLMapper.update(newUser);
//
//        // xml mapper 에 의한 이름으로 조회 select
//        log.info("##################################################################################################");
//        log.info("xml Mapper 에 의한 조회 : " + userXMLMapper.selectUserByname("더블세븐2_이름바뀜"));
//        log.info("##################################################################################################");
//    }

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
