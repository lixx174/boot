package com.boot.admin.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 用户新增参数
 *
 * @author jinx
 */
@Getter
@Setter
public class RoleOfferCommand {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 资源id
     */
    private Set<Integer> resourceIds;
}
