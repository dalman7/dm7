package kr.co.dm7.blackpink.repository.mapper;

import kr.co.dm7.blackpink.domain.Subscriber;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserXMLMapper {

    void insert(Subscriber user);

    Subscriber select(String id);

    Subscriber selectUserByname(String name);

    Integer update(Subscriber user);

    void delete(String id);
}