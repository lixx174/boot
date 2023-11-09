package com.boot.admin.application.dto.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 用户修改参数
 *
 * @author jinx
 */
@Getter
@Setter
public class RoleModifyCommand {

    /**
     * 表id
     */
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 资源id
     */
    private Set<Integer> resources;
}
