package kr.co.dm7.blackpink.service;

import kr.co.dm7.blackpink.domain.User;

import java.util.List;

/**
 * User 서비스 정보다.
 *
 * @author doubleseven
 * @version 1.0
 */
public interface UserService {

    /**
     * User 정보를 얻는다.
     *
     * @param id
     * @return
     */
    User getUser(String id);

    /**
     * User 의 모든 정보를 얻는다.
     *
     * @return
     */
    List<User> getUsers();

    /**
     * Usser 정보를 세팅한다.
     *
     * @param user
     */
    void setUser(User user);

    /**
     * User 정보를 삭제한다.
     *
     * @param id
     */
    void removeUser(String id);
}