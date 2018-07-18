package kr.co.dm7.blackpink.service;

import kr.co.dm7.blackpink.domain.User;
import kr.co.dm7.blackpink.repository.map.UserMapRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User 정보에 대한 Service 구현이다.
 * 나중에 필요할때 제대로 수정될 예정이다.
 */
@Service
public class UserServiceImpl implements UserService {

    @Setter(onMethod = @__({@Autowired}))
    private UserMapRepository userMapRepository;

    @Override
    public User getUser(String id) {
        return userMapRepository.getOne(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapRepository.getAll();
    }

    @Override
    public void setUser(User user) {
        userMapRepository.setOne(user);
    }

    @Override
    public void removeUser(String id) {
        userMapRepository.remove(id);
    }
}
