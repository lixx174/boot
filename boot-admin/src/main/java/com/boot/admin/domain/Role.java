package com.boot.admin.domain;

import com.boot.admin.domain.primitive.ResourceId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class Role {

    public Role(String name, String remark, Set<Integer> resourceIds) {
        this.name = name;
        this.remark = remark;
        this.resourceIds = resourceIds == null ? Set.of() :
                resourceIds.stream()
                        .map(ResourceId::new)
                        .collect(Collectors.toSet());
    }

    private Integer id;

    private String name;

    private String remark;

    private LocalDateTime createAt = LocalDateTime.now();

    private Set<ResourceId> resourceIds;

    public boolean hasResource() {
        return !resourceIds.isEmpty();
    }

    public Set<Integer> getPrimitiveResourceIds() {
        return resourceIds.stream()
                .map(ResourceId::getValue)
                .collect(Collectors.toSet());
    }
}
