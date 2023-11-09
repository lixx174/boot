package com.boot.admin.api;

import com.boot.admin.application.PageReply;
import com.boot.admin.application.Result;
import com.boot.admin.application.dto.ResourceDto;
import com.boot.admin.application.dto.command.ResourceModifyCommand;
import com.boot.admin.application.dto.command.ResourceOfferCommand;
import com.boot.admin.application.dto.query.ResourcePageQuery;
import com.boot.admin.application.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 资源管理
 *
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("resource")
public class ResourceController {

    private final ResourceService service;


    /**
     * 分页
     *
     * @param query 分页参数
     * @return 分页数据
     */
    @GetMapping("page")
    public Result<PageReply<ResourceDto>> page(ResourcePageQuery query) {
        return Result.ok(service.page(query));
    }

    /**
     * 资源详情
     *
     * @param id 资源id
     * @return 资源信息
     */
    @GetMapping
    public Result<ResourceDto> detail(Integer id) {
        return Result.ok(service.detail(id));
    }


    /**
     * 新增
     *
     * @param command 新增参数
     * @return void
     */
    @PostMapping
    public Result<Void> offer(@Valid @RequestBody ResourceOfferCommand command) {
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
    public Result<Void> modify(@Valid @RequestBody ResourceModifyCommand command) {
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
    public Result<Void> remove(@RequestBody Set<Integer> ids) {
        service.remove(ids);
        return Result.ok();
    }

    /**
     * 资源树
     *
     * @return 资源树模型根节点
     */
    @GetMapping("tree")
    public Result<ResourceDto> tree() {
        return Result.ok(service.tree());
    }
}
