package com.boot.admin.application.dto.command;

import com.boot.admin.domain.enums.Sex;
import com.boot.admin.domain.enums.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户修改参数
 *
 * @author Jinx
 */
@Getter
@Setter
public class UserModifyCommand {

    /**
     * 表id
     */
    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式异常")
    private String mobile;
    /**
     * 头像
     */
    private String avatar;
    /**
     * MYSTERY 保密 | MALE 男性 | FEMALE 女性
     */
    private Sex sex;
    /**
     * ENABLE 正常 | DISABLE 禁用
     */
    private UserStatus status;
}
