package com.boot.admin.application.assembler;

import com.boot.admin.application.dto.ResourceDto;
import com.boot.admin.domain.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper
public interface ResourceAssembler {

    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "children", ignore = true),
    })
    ResourceDto assemble(Resource resource);

    List<ResourceDto> assemble(List<Resource> resources);

}
