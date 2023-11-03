package com.boot.admin.infra.repository.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.admin.domain.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName("sys_resource")
public class ResourceDO {

    private Integer id;

    private Integer pid;

    private String name;

    private String permission;

    private String uri;

    private String icon;

    private ResourceType type;

    private LocalDateTime createAt;
}
