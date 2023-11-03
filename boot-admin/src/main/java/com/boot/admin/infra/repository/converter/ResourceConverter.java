package com.boot.admin.infra.repository.converter;

import com.boot.admin.domain.Resource;
import com.boot.admin.infra.repository.model.ResourceDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper
public interface ResourceConverter {


    @Mappings({
            @Mapping(target = "id.value", source = "id"),
            @Mapping(target = "pid.value", source = "pid"),
            @Mapping(target = "children", ignore = true),
    })
    Resource convert(ResourceDO resource);


    List<Resource> convert(List<ResourceDO> resources);
}
