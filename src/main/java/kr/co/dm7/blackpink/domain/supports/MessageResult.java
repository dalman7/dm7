package kr.co.dm7.blackpink.domain.supports;

import lombok.Builder;
import lombok.Data;

/**
 * 결과를 반환하기 위한 Simple Type 의 Message 객체이다.
 *
 * @author doubleseven
 * @version 1.0
 */
@Data
@Builder
public class MessageResult {

    private boolean success;

    private String messsage;
}