package com.boot.admin.application.assembler;

import com.boot.admin.application.dto.RoleDto;
import com.boot.admin.application.dto.command.RoleModifyCommand;
import com.boot.admin.application.dto.command.RoleOfferCommand;
import com.boot.admin.domain.Role;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper(uses = ResourceAssembler.class)
public interface RoleAssembler {

    @Named("standard")
    @Mappings({
            @Mapping(target = "resources", ignore = true),
    })
    RoleDto assemble(Role role);

    @IterableMapping(qualifiedByName = "standard")
    List<RoleDto> assemble(List<Role> roles);

    @Mapping(target = "resources", qualifiedByName = "ResourceAssembler.assembleShortly")
    RoleDto assembleForDetail(Role role);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
    })
    Role assemble(RoleOfferCommand command);

    @Mappings({
            @Mapping(target = "createAt", expression = "java(null)"),
    })
    Role assemble(RoleModifyCommand command);
}
