package com.boot.admin.infra.repository.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("sys_role")
public class RoleDO {

    private Integer id;

    private String name;

    private String remark;

    private LocalDateTime createAt;
}
