package com.boot.admin.infra.repository.converter;

import com.boot.admin.domain.Role;
import com.boot.admin.infra.repository.model.RoleDo;
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
            @Mapping(target = "resources", ignore = true),
    })
    Role convert(RoleDo role);

    List<Role> convert(List<RoleDo> roles);

    Role convert(RoleDo role, Set<Integer> resources);

    RoleDo convert(Role role);
}
