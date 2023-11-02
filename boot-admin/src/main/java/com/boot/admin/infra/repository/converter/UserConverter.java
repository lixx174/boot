package com.boot.admin.infra.repository.converter;

import com.boot.admin.domain.User;
import com.boot.admin.infra.repository.model.UserDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper
public interface UserConverter {

    @Mappings({
            @Mapping(target = "password", source = "password.value"),
            @Mapping(target = "mobile", source = "mobile.value"),
    })
    UserDo convert(User user);

    @Mappings({
            @Mapping(target = "password.value", source = "password"),
            @Mapping(target = "mobile.value", source = "mobile"),
    })
    User convert(UserDo user);

    @Mappings({
            @Mapping(target = "password.value", source = "password"),
            @Mapping(target = "mobile.value", source = "mobile"),
    })
    List<User> convert(List<UserDo> users);
}
