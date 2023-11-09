package com.boot.admin.api;

import com.boot.admin.application.PageReply;
import com.boot.admin.application.Result;
import com.boot.admin.application.dto.RoleDto;
import com.boot.admin.application.dto.command.RoleModifyCommand;
import com.boot.admin.application.dto.command.RoleOfferCommand;
import com.boot.admin.application.dto.query.RolePageQuery;
import com.boot.admin.application.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理
 *
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("role")
public class RoleController {

    private final RoleService service;


    /**
     * 分页
     *
     * @param query 分页参数
     * @return 分页数据
     */
    @GetMapping("page")
    public Result<PageReply<RoleDto>> page(RolePageQuery query) {
        return Result.ok(service.page(query));
    }

    /**
     * 详情
     *
     * @param id 角色id
     * @return 角色详细信息
     */
    @GetMapping
    public Result<RoleDto> detail(Integer id) {
        return Result.ok(service.detail(id));
    }

    /**
     * 新增
     *
     * @param command 新增参数
     * @return void
     */
    @PostMapping
    public Result<Void> offer(@Valid @RequestBody RoleOfferCommand command) {
        service.offer(command);
        return Result.ok();
    }

    /**
     * 修改
     *
     * @param command 修改参数
     * @return void
     */
    @PutMapping
    public Result<Void> modify(@Valid @RequestBody RoleModifyCommand command) {
        service.modify(command);
        return Result.ok();
    }
}
