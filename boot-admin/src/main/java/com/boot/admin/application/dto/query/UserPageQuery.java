package com.boot.admin.application.dto.query;

import com.boot.admin.application.PageQuery;
import com.boot.admin.domain.Specification;
import com.boot.admin.domain.enums.Sex;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户分页查询
 *
 * @author Jinx
 */
@Getter
@Setter
public class UserPageQuery extends PageQuery {

    /**
     * 名字
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别
     */
    private Sex sex;


    @Override
    public Set<Specification> buildSpecification() {
        return new HashSet<>() {{
            if (StringUtils.hasText(name)) {
                add(Specification.of("name", name));
            }
            if (StringUtils.hasText(mobile)) {
                add(Specification.of("mobile", mobile));
            }
            if (sex != null) {
                add(Specification.of("sex", sex));
            }
        }};
    }
}
