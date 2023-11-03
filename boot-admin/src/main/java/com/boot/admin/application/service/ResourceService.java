package com.boot.admin.application.service;

import com.boot.admin.application.PageReply;
import com.boot.admin.application.Result;
import com.boot.admin.application.assembler.ResourceAssembler;
import com.boot.admin.application.dto.ResourceDto;
import com.boot.admin.application.dto.query.ResourcePageQuery;
import com.boot.admin.domain.Resource;
import com.boot.admin.domain.repository.ResourceRepository;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageRequestImpl;
import com.boot.admin.domain.repository.page.PageResponse;
import com.boot.admin.domain.repository.page.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository repo;
    private final ResourceAssembler assembler;


    public PageReply<ResourceDto> page(ResourcePageQuery query) {
        PageRequest pageRequest = PageRequestImpl.of(
                query.getCurrent(),
                query.getSize(),
                query.buildSpecification().toArray(new Specification[0])
        );
        PageResponse<Resource> pageResponse = repo.findAll(pageRequest);

        return PageReply.of(
                pageRequest.getCurrent(), pageRequest.getSize(),
                pageResponse.getPages(), assembler.assemble(pageResponse.getContents())
        );
    }

    public List<ResourceDto> tree() {
        return assembler.assemble(repo.findAll());
    }
}
