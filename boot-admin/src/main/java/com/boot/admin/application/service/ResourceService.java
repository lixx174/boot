package com.boot.admin.application.service;

import com.boot.admin.application.PageReply;
import com.boot.admin.application.assembler.ResourceAssembler;
import com.boot.admin.application.dto.ResourceDto;
import com.boot.admin.application.dto.command.ResourceModifyCommand;
import com.boot.admin.application.dto.command.ResourceOfferCommand;
import com.boot.admin.application.dto.query.ResourcePageQuery;
import com.boot.admin.domain.Resource;
import com.boot.admin.domain.repository.ResourceRepository;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageRequestImpl;
import com.boot.admin.domain.repository.page.PageResponse;
import com.boot.admin.domain.repository.page.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

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
                pageResponse.getPages(), assembler.assembleForPage(pageResponse.getContents())
        );
    }


    public ResourceDto detail(Integer id) {
        return assembler.assembleForDetail(repo.findById(id));
    }

    public void offer(ResourceOfferCommand command) {
        repo.save(assembler.assemble(command));
    }

    public void modify(ResourceModifyCommand command) {
        repo.save(assembler.assemble(command));
    }

    public void remove(Set<Integer> ids) {
        repo.removeAllById(ids);
    }

    public ResourceDto tree() {
        Resource root = Resource.ResourceTreeBuilder
                .from(repo.findAll())
                .build();

        return assembler.assembleForTree(root);
    }
}
