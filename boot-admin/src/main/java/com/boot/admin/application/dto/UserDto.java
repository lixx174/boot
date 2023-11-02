package com.boot.admin.application.dto;

import com.boot.admin.domain.enums.Sex;
import com.boot.admin.domain.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author jinx
 */
@Getter
@Setter
public class UserDto {

    /**
     * 表id
     */
    private Serializable id;
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
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
}
