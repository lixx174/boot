package com.boot.admin.domain.repository.page;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * FIXME 抽象一层接口 提供一个反射实现方式
 *
 * @author Jinx
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Specification {

    private final String column;
    private final Object value;
    private final Operator operator;


    public static Specification of(String column, Object value) {
        return of(column, value, Operator.EQUALS);
    }

    public static Specification of(String column, Object value, Operator operator) {
        return new Specification(column, value, operator);
    }

    public enum Operator {
        EQUALS,
        LIKE
    }
}
