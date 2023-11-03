package com.boot.admin.api;

import com.boot.admin.application.PageReply;
import com.boot.admin.application.Result;
import com.boot.admin.application.dto.ResourceDto;
import com.boot.admin.application.dto.query.ResourcePageQuery;
import com.boot.admin.application.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 资源树
     *
     * @return 资源树模型
     */
    @GetMapping("tree")
    public Result<List<ResourceDto>> tree() {
        return Result.ok(service.tree());
    }
}
