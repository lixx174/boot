package com.boot.authorization.application;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Jinx
 */
//@Getter
//@Setter
public class HomeEvent {

    /**
     *  3.2.0 的springboot才支持
     *
     */
    @JsonCreator
    public HomeEvent(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private Integer age;
}
