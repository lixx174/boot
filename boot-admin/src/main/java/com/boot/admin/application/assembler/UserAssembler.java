package com.boot.admin.application.assembler;

import com.boot.admin.application.dto.UserDto;
import com.boot.admin.application.dto.command.UserModifyCommand;
import com.boot.admin.application.dto.command.UserOfferCommand;
import com.boot.admin.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper
public interface UserAssembler {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "avatar", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "password.value", source = "password"),
            @Mapping(target = "mobile.value", source = "mobile"),
    })
    User assemble(UserOfferCommand command);

    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "mobile.value", source = "mobile"),
            @Mapping(target = "createAt", expression = "java(null)"),
    })
    User assemble(UserModifyCommand command);


    @Mappings({
            @Mapping(target = "mobile", source = "mobile.value"),
    })
    UserDto assemble(User user);

    @Mappings({
            @Mapping(target = "mobile", source = "mobile.value"),
    })
    List<UserDto> assemble(List<User> users);
}
