package com.boot.admin.application.dto.command;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户修改密码参数
 *
 * @author Jinx
 */
@Getter
@Setter
public class UserPasswordModifyCommand {

    /**
     * 表id
     */
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 原密码
     */
    @NotNull(message = "原密码不能为空")
    private String originalPassword;
    /**
     * 新密码
     */
    @NotNull(message = "新密码不能为空")
    private String latestPassword;
}
