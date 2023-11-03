package com.boot.admin.api;

import com.boot.admin.application.PageReply;
import com.boot.admin.application.Result;
import com.boot.admin.application.dto.UserDto;
import com.boot.admin.application.dto.command.UserModifyCommand;
import com.boot.admin.application.dto.command.UserOfferCommand;
import com.boot.admin.application.dto.command.UserPasswordModifyCommand;
import com.boot.admin.application.dto.query.UserPageQuery;
import com.boot.admin.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户管理
 *
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService service;


    /**
     * 分页
     *
     * @param query 分页参数
     * @return 分页数据
     */
    @GetMapping("page")
    public Result<PageReply<UserDto>> page(UserPageQuery query) {
        return Result.ok(service.page(query));
    }

    /**
     * 详情
     *
     * @param id 用户id
     * @return 用户详细信息
     */
    @GetMapping
    public Result<UserDto> detail(Serializable id) {
        return Result.ok(service.detail(id));
    }

    /**
     * 新增
     *
     * @param command 新增参数
     * @return void
     */
    @PostMapping
    public Result<Void> offer(@Valid @RequestBody UserOfferCommand command) {
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
    public Result<Void> modify(@Valid @RequestBody UserModifyCommand command) {
        service.modify(command);
        return Result.ok();
    }

    /**
     * 删除
     *
     * @param ids 用户id数组
     * @return void
     */
    @DeleteMapping
    public Result<Void> remove(@RequestBody Set<Serializable> ids) {
        service.remove(ids);
        return Result.ok();
    }


    /**
     * 登录个人信息
     *
     * @return 个人信息
     */
    @GetMapping("profile")
    public Result<UserDto> profile() {
        return Result.ok(service.profile());
    }

    /**
     * 修改密码
     *
     * @param command 修改密码参数
     * @return void
     */
    @PutMapping("password")
    public Result<Void> passwordModify(@Valid @RequestBody UserPasswordModifyCommand command) {
        service.passwordModify(command);
        return Result.ok();
    }
}
