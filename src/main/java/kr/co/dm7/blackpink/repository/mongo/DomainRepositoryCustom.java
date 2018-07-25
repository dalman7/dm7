package kr.co.dm7.blackpink.repository.mongo;

/**
 * custom 하게 쿼리할 경우를 위해서 만듬
 * MongoTemplate 을 이용해서 다양하고 복잡한 쿼리를 수행하는 경우에 사용할 수 있다.
 *
 * @author doubleseven
 * @version 1.0
 */
public interface DomainRepositoryCustom {

    long updateDisplay(String name, boolean display);
}
