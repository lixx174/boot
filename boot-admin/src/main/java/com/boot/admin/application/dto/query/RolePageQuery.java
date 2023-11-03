package com.boot.admin.application.dto.query;

import com.boot.admin.application.PageQuery;
import com.boot.admin.domain.repository.page.Specification;
import com.boot.admin.domain.repository.page.Specification.Operator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 资源分页查询
 *
 * @author Jinx
 */
@Getter
@Setter
public class RolePageQuery extends PageQuery {

    /**
     * 名字
     */
    private String name;


    @Override
    public Set<Specification> buildSpecification() {
        return new HashSet<>() {{
            if (StringUtils.hasText(name)) {
                add(Specification.of("name", name, Operator.LIKE));
            }
        }};
    }
}
