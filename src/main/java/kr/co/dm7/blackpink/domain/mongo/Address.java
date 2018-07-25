package kr.co.dm7.blackpink.domain.mongo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String postCode;

    private String firstAddress;

    private String secondAddress;
}
