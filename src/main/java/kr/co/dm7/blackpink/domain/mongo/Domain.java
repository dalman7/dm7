package kr.co.dm7.blackpink.domain.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * 테스트를 위해서 domain 이라는 객체를 하나 만들었음
 */
@Data
@Builder
@Document(collection = "domain")
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Indexed(unique = true)
    private String name;

    private String url;

    private Address address;

    private boolean display;
}
