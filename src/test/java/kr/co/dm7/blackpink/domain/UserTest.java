package kr.co.dm7.blackpink.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * 사용할지 안할지는 모르겠지만 기본적인 User 객체와 Lombok 을 이용했을때 생성되는 getter, setter 에 대한 기능 테스트다
 * 추후 User 객체를 Security 에서 사용하거나 다른 Domain 객체 정보르도 사용할 수 있기는 하겠지만
 * 안쓰게 될 때는 테스트 코드를 포함하여 대상 코드를 삭제할 예정이다.
 *
 * @author doubleseven
 * @version 1.0
 */
public class UserTest {

    User user;

    @Before
    public void before() {
        user = new User();
    }

    @Test
    public void 기본_롬복_getter_setter_테스트() {
        user.setId("doubleseven");
        user.setName("더블세븐");

        assertThat(user.getId(), is("doubleseven"));
        assertThat(user.getName(), is("더블세븐"));
    }
}