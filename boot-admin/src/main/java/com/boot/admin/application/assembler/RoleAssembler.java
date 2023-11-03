package com.boot.admin.application.assembler;

import com.boot.admin.application.dto.RoleDto;
import com.boot.admin.domain.Role;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper
public interface RoleAssembler {

    RoleDto assemble(Role role);

    List<RoleDto> assemble(List<Role> roles);

}
