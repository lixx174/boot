package com.boot.admin.application.assembler;

import com.boot.admin.application.dto.ResourceDto;
import com.boot.admin.application.dto.command.ResourceModifyCommand;
import com.boot.admin.application.dto.command.ResourceOfferCommand;
import com.boot.admin.domain.Resource;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper
public interface ResourceAssembler {

    @Named("page")
    @Mappings({
            @Mapping(target = "parent", ignore = true),
            @Mapping(target = "children", ignore = true),
    })
    ResourceDto assembleForPage(Resource resource);

    @IterableMapping(qualifiedByName = "page")
    List<ResourceDto> assembleForPage(List<Resource> resources);

    default ResourceDto assembleForDetail(Resource resource) {
        ResourceDto dto = assembleForPage(resource);

        if (resource.getParent() != null) {
            dto.setParent(new ResourceDto() {{
                setId(resource.getParent().getId());
                setName(resource.getParent().getName());
            }});
        }

        return dto;
    }

    @Named("tree")
    default ResourceDto assembleForTree(Resource resource) {
        if (resource == null) {
            return null;
        }

        return new ResourceDto() {{
            setId(resource.getId());
            setName(resource.getName());
            setChildren(assembleForTree(resource.getChildren()));
        }};
    }

    @IterableMapping(qualifiedByName = "tree")
    List<ResourceDto> assembleForTree(List<Resource> resources);

    @Named("ResourceAssembler.assembleShortly")
    @Mappings({
            @Mapping(target = "parent", ignore = true),
            @Mapping(target = "permission", ignore = true),
            @Mapping(target = "uri", ignore = true),
            @Mapping(target = "icon", ignore = true),
            @Mapping(target = "type", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "children", ignore = true),
    })
    ResourceDto assembleShortly(Resource resource);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "parent.id", source = "pid"),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "children", ignore = true),
    })
    Resource assemble(ResourceOfferCommand command);

    @Mappings({
            @Mapping(target = "parent.id", source = "pid"),
            @Mapping(target = "createAt", expression = "java(null)"),
            @Mapping(target = "children", ignore = true),
    })
    Resource assemble(ResourceModifyCommand command);
}
