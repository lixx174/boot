package com.boot.admin.application.dto.command;

import com.boot.admin.domain.enums.Sex;
import com.boot.admin.domain.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户新增参数
 *
 * @author jinx
 */
@Getter
@Setter
public class UserOfferCommand {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式异常")
    private String mobile;
    /**
     * MYSTERY 保密 | MALE 男性 | FEMALE 女性
     */
    @NotNull(message = "性别不能为空")
    private Sex sex;
    /**
     * ENABLE 正常 | DISABLE 禁用
     */
    @NotNull(message = "性别不能为空")
    private UserStatus status;
}
