package kr.co.dm7.blackpink.repository.mapper;

import kr.co.dm7.blackpink.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserXMLMapper {

    void insert(User user);

    User select(String id);

    User selectUserByname(String name);

    Integer update(User user);
}