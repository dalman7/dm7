package kr.co.dm7.blackpink.repository.map;

import java.util.List;

/**
 * Map 기반으로 된 Repository 에서 정보를 가져오고자 하는 경우 사용된다.
 * 사실은 Spring 에 이미 훌륭하게 구현이 되어 있으므로 불필요하지만.. (걍 쓰면 되니까)
 * 학습용으로 중간단계가 있으면 좋을 것 같아서 개념 정리 차원에서 이렇게 구현해 둔다.
 * 따라서 이 소스는 뭐다? 펑 하고 사라질 운명이라는 것.
 *
 * @param <T>  domain 타입
 * @param <ID> 키를 식발할 ID 타입
 * @author doubleseven
 * @version 1.0
 */
public interface MapRepository<T, ID> {
    T getOne(ID id);

    List<T> getAll();

    void setOne(T user);

    void remove(ID id);
}
