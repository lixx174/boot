package com.boot.admin.domain;

import com.boot.admin.domain.enums.Sex;
import com.boot.admin.domain.enums.UserStatus;
import com.boot.admin.domain.excption.UnprocessableException;
import com.boot.admin.domain.primitive.Mobile;
import com.boot.admin.domain.primitive.Password;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * TODO mapstruct即使无空参构造函数也能转换
 *
 * @author jinx
 */
@Getter
@Setter
public class User {

    private Integer id;

    private String username;

    private Password password;

    private String name;

    private Mobile mobile;

    private String avatar;

    private Sex sex;

    private UserStatus status;

    private LocalDateTime createAt = LocalDateTime.now();


    public void modifyPassword(Password originalPassword, Password latestPassword) {
        if (password.matches(originalPassword)) {
            password = latestPassword;
        } else {
            throw new UnprocessableException("original password not match");
        }
    }
}
