package com.boot.admin.infra.repository.converter;

import com.boot.admin.domain.Role;
import com.boot.admin.infra.repository.model.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

/**
 * @author Jinx
 */
@Mapper
public interface RoleConverter {

    @Mappings({
            @Mapping(target = "resourceIds", ignore = true),
            @Mapping(target = "primitiveResourceIds", ignore = true),
    })
    Role convert(RoleDO role);

    List<Role> convert(List<RoleDO> roles);

    RoleDO convert(Role role);

    @Mappings({
            @Mapping(target = "primitiveResourceIds", ignore = true),
    })
    Role convert(RoleDO role, Set<Integer> resourceIds);
}
