package com.boot.admin.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoleDto {

    private Integer id;

    private String name;

    private String remark;

    private LocalDateTime createAt;
}
