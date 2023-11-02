package com.boot.admin.infra.repository.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.admin.domain.enums.Sex;
import com.boot.admin.domain.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author jinx
 */
@Getter
@Setter
@TableName("sys_user")
public class UserDo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String mobile;

    private String avatar;

    private Sex sex;

    private UserStatus status;

    private LocalDateTime createAt;
}
