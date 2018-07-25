package kr.co.dm7.blackpink.web.controller;

import kr.co.dm7.blackpink.domain.Subscriber;
import kr.co.dm7.blackpink.domain.supports.MessageResult;
import kr.co.dm7.blackpink.repository.mapper.UserMapper;
import kr.co.dm7.blackpink.repository.mapper.UserXMLMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MybatisUserController {

    @Setter(onMethod = @__({@Autowired}))
    private UserMapper userMapper;

    @Setter(onMethod = @__({@Autowired}))
    private UserXMLMapper userXMLMapper;

    /**
     * User 정보 한건을 얻는다
     * ex) http://localhost:8080/mybatis/user/1
     *
     * @param id
     * @return
     */
    @GetMapping("/mybatis/user/{id}")
    public Subscriber getUser(@PathVariable String id) {
        return userMapper.findOne(id);
    }

    /**
     * User 전체 정보를 얻는다.
     * ex) http://localhost:8080/mybatis/users
     *
     * @return
     */
    @GetMapping("/mybatis/users")
    public List<Subscriber> getUsers() {
        return userMapper.findAll();
    }

    /**
     * User 정보를 등록/수정 한다
     * ex) [POST] http://localhost:8080/mybatis/user
     * <p>
     * body
     * {
     * "id": 4,
     * "name": "테스트",
     * "nick": "닉네임"
     * }
     *
     * @param user
     */
    @PutMapping(value = "/mybatis/user", consumes = "application/json", produces = "application/json")
    public MessageResult addUser(@RequestBody Subscriber user) {
        Subscriber dbuser = userXMLMapper.select(String.valueOf(user.getId()));
        if (dbuser == null) {
            userXMLMapper.insert(user);
        } else {
            userXMLMapper.update(user);
        }
        return MessageResult
                .builder()
                .success(Boolean.TRUE)
                .messsage("성공적으로 적용했습니다.")
                .build();
    }

    /**
     * User 정보를 삭제한다.
     * ex) [DELETE] http://localhost:8080/mybatis/user/5
     * @param id
     */
    @DeleteMapping("/mybatis/user/{id}")
    public MessageResult remove(@PathVariable String id) {
        userXMLMapper.delete(id);
        return MessageResult
                .builder()
                .success(Boolean.TRUE)
                .messsage("성공적으로 삭제했습니다.")
                .build();
    }
}
