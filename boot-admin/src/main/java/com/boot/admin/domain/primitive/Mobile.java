package com.boot.admin.domain.primitive;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class Mobile {

    private String value;

    public Mobile(String value) {
        // FIXME validation已经校验过参数 如果不依赖校验组件 此处需要实现校验逻辑
        this.value = value;
    }
}
