package kr.co.dm7.blackpink.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("kr.co.dm7.blackpink.repository.mapper")
public class MyBatisMapperConfig {

}
