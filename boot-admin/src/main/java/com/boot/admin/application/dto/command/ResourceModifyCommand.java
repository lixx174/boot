package com.boot.admin.application.dto.command;

import com.boot.admin.domain.enums.ResourceType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 资源信息
 */
@Getter
@Setter
public class ResourceModifyCommand {

    @NotNull(message = "资源id不能为空")
    private Integer id;
    /**
     * 父级资源id
     */
    private Integer pid;
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
}
