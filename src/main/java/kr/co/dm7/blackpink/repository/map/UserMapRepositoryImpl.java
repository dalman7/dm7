package kr.co.dm7.blackpink.repository.map;

import kr.co.dm7.blackpink.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 학습용으로 만든 맵 Repository 다
 * 이거 다 사라질 소스인거 아시죠?
 *
 * @author doubleseven
 * @version 1.0
 */
@Repository
public class UserMapRepositoryImpl implements UserMapRepository {

    Map<String, User> userMap = new HashMap<>();

    @Override
    public User getOne(String id) {
        return userMap.get(id);
    }

    @Override
    public List<User> getAll() {
        return userMap.values().stream()
                .collect(Collectors.toList());
    }

    @Override
    public void setOne(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void remove(String id) {
        userMap.remove(id);
    }
}
