package com.boot.admin.application;

import com.boot.admin.domain.Specification;
import lombok.Getter;

import java.util.Set;

/**
 * 分页请求基础模型  所有分页模型都需要继承它
 * <p>
 * 具体模型看子类
 *
 * @author jinx
 */
@Getter
public class PageQuery {

    /**
     * 当前页码(默认1)
     */
    private Number current = 1;
    /**
     * 分页大小(默认10)
     */
    private Number size = 10;

    /**
     * 构建条件
     *
     * @return 条件集合
     */
    public Set<Specification> buildSpecification() {
        return Set.of();
    }
}
