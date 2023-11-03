# 基础项目

1. boot-admin 后台相关（开发中）
2. boot-applet 小程序相关（待开发）
3. boot-authorization 认证中心（待开发）

## 技术栈：

使用DDD思想进行落地，总体采用4层+CQRS架构。

Spring Boot + Mybatis Plus + Spring Authorization Server

## 核心组件介绍

~~~
api：视图层，负责与客户端交互，接受请求转发（只会依赖application层）
application：应用层，负责业务编排（只会依赖到domain层）
    - assembler：dto与domain之间的转换器
    - dto：与客户端交互的数据模型
        - command：命令请求模型
        - query：查询请求模型
        - event：事件请求模型
domain：领域层，具体的业务处理（不依赖任何层）
    - enums：枚举
    - primitive：原始领域（可复用的模型）
    - repository：仓储定义
        - page：分页模型
    - service：领域服务    
infra：底层实现，根据依赖倒转原则将其作为顶层（它可以依赖其他任何层）
    - config：项目配置
    - exception：控制器全局异常处理器
    - repository：domain仓储的具体实现
        - analyzer：domain分页模型的解析器
        - converter：do与domain之间的转换器
        - model：do模型
~~~