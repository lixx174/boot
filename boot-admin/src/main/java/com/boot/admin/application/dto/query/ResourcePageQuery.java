package com.boot.admin.application.dto.query;

import com.boot.admin.application.PageQuery;
import com.boot.admin.domain.enums.ResourceType;
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
public class ResourcePageQuery extends PageQuery {

    /**
     * 名字
     */
    private String name;
    /**
     * BUTTON 菜单 | ROUTE 路由
     */
    private ResourceType type;


    @Override
    public Set<Specification> buildSpecification() {
        return new HashSet<>() {{
            if (StringUtils.hasText(name)) {
                add(Specification.of("name", name, Operator.LIKE));
            }
            if (type != null) {
                add(Specification.of("type", type));
            }
        }};
    }
}
