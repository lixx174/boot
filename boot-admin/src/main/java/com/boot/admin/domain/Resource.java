package com.boot.admin.domain;

import com.boot.admin.domain.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class Resource {

    Resource(Integer id) {
        this.id = id;
    }

    public Resource(Resource parent, String name, String permission,
                    String uri, String icon, ResourceType type) {
        if (type == ResourceType.ROUTE) {
            if (uri == null || uri.isEmpty()) {
                throw new IllegalArgumentException("资源为路由时, uri不能为空");
            }
            if (icon == null || icon.isEmpty()) {
                throw new IllegalArgumentException("资源为路由时, icon不能为空");
            }
        }

        this.parent = parent;
        this.name = name;
        this.permission = permission;
        this.type = type;
        this.uri = uri;
        this.icon = icon;
    }

    private Integer id;

    private Resource parent;

    private String name;

    private String permission;

    private String uri;

    private String icon;

    private ResourceType type;

    private LocalDateTime createAt = LocalDateTime.now();

    private List<Resource> children = new ArrayList<>();


    public static class ResourceTreeBuilder {

        private ResourceTreeBuilder(List<Resource> resources) {
            root = new Resource(0);
            groups = resources.stream().collect(Collectors.groupingBy(resource -> resource.getParent().getId()));
        }

        private final Resource root;
        private final Map<Integer, List<Resource>> groups;


        public static ResourceTreeBuilder from(List<Resource> resources) {
            return new ResourceTreeBuilder(resources == null ? Collections.emptyList() : resources);
        }


        public Resource build() {
            return doBuild(root);
        }

        private Resource doBuild(Resource current) {
            List<Resource> children = groups.get(current.getId());

            if (children != null) {
                for (Resource child : children) {
                    current.getChildren().add(doBuild(child));
                }
            }

            return current;
        }
    }
}
