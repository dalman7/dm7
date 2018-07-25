package kr.co.dm7.blackpink.repository.mongo;

import com.mongodb.client.result.UpdateResult;
import kr.co.dm7.blackpink.domain.mongo.Domain;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * MongoTemplate 을 이용해서 비교적 자유롭게 쿼리 하여 다양한 결과를 리턴한다.
 *
 * @author doubleseven
 * @version 1.0
 */
public class DomainRepositoryImpl implements DomainRepositoryCustom {

    @Setter(onMethod = @__({@Autowired}))
    private MongoTemplate mongoTemplate;

    @Override
    public long updateDisplay(String name, boolean display) {
        UpdateResult result = mongoTemplate.updateMulti(
                new Query(Criteria.where("name").is(name)),
                new Update() {{
                    set("display", display);
                }},
                Domain.class);


        return (result == null) ? 0 : result.getModifiedCount();
    }
}
