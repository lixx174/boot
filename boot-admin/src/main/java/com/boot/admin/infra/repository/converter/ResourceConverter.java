package com.boot.admin.infra.repository.converter;

import com.boot.admin.domain.Resource;
import com.boot.admin.infra.repository.model.ResourceDo;
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
            @Mapping(target = "parent.id", source = "pid"),
            @Mapping(target = "children", ignore = true),
    })
    Resource convert(ResourceDo resource);

    List<Resource> convert(List<ResourceDo> resources);

    @Mappings({
            @Mapping(target = "pid", source = "parent.id"),
    })
    ResourceDo convert(Resource resource);
}
