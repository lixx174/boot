package com.boot.admin.application.dto;

import com.boot.admin.domain.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源信息
 */
@Getter
@Setter
public class ResourceDto {

    private Integer id;
    /**
     * 父级资源id
     */
    private ResourceDto parent;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源权限值
     */
    private String permission;
    /**
     * 路由uri
     */
    private String uri;
    /**
     * 路由图标
     */
    private String icon;
    /**
     * BUTTON 菜单 | ROUTE 路由
     */
    private ResourceType type;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
    /**
     * 下级资源
     */
    private List<ResourceDto> children = new ArrayList<>();
}
