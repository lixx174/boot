package com.boot.admin.domain.primitive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
@AllArgsConstructor
public class Password {

    private String value;


    public boolean matches(Password password) {
        return value.equals(password.getValue());
    }
}
