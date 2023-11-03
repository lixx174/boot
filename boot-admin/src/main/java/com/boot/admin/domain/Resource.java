package com.boot.admin.domain;

import com.boot.admin.domain.enums.ResourceType;
import com.boot.admin.domain.primitive.ResourceId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class Resource {

    private ResourceId id;

    private ResourceId pid;

    private String name;

    private String permission;

    private String uri;

    private String icon;

    private ResourceType type;

    private LocalDateTime createAt = LocalDateTime.now();

    private Set<ResourceId> children;
}
