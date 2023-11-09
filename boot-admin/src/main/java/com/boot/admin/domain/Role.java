package com.boot.admin.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class Role {

    private Integer id;
    private String name;
    private String remark;
    private LocalDateTime createAt = LocalDateTime.now();
    private Set<Resource> resources;

    public Role(String name, String remark, Set<Integer> resources) {
        this.name = name;
        this.remark = remark;
        this.resources = resources == null ?
                Set.of() :
                resources.stream().map(Resource::new).collect(Collectors.toSet());
    }

    public boolean hasResource() {
        return !resources.isEmpty();
    }
}
