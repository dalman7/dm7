package kr.co.dm7.blackpink.repository.mapper;

import kr.co.dm7.blackpink.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findOne(@Param("id") String id);

}
