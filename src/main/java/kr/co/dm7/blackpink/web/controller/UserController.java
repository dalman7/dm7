package kr.co.dm7.blackpink.web.controller;

import kr.co.dm7.blackpink.domain.User;
import kr.co.dm7.blackpink.domain.supports.MessageResult;
import kr.co.dm7.blackpink.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Setter(onMethod = @__({@Autowired}))
    private UserService userService;

    /**
     * User 정보 한건을 얻는다
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    /**
     * User 전체 정보를 얻는다.
     *
     * @return
     */
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * User 정보를 수정한다
     *
     * @param user
     */
    @PutMapping(value = "/user", consumes = "application/json", produces = "application/json")
    public MessageResult setUser(@RequestBody User user) {
        userService.setUser(user);
        return MessageResult
                .builder()
                .success(Boolean.TRUE)
                .messsage("성공적으로 적용했습니다.")
                .build();

    }

    /**
     * User 정보를 삭제한다.
     *
     * @param id
     */
    @DeleteMapping("/user/{id}")
    public MessageResult remove(@PathVariable String id) {
        userService.removeUser(id);
        return MessageResult
                .builder()
                .success(Boolean.TRUE)
                .messsage("성공적으로 삭제했습니다.")
                .build();
    }
}