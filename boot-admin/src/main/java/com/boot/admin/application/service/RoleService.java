package com.boot.admin.application.service;

import com.boot.admin.application.PageReply;
import com.boot.admin.application.assembler.RoleAssembler;
import com.boot.admin.application.dto.RoleDto;
import com.boot.admin.application.dto.command.RoleModifyCommand;
import com.boot.admin.application.dto.command.RoleOfferCommand;
import com.boot.admin.application.dto.query.RolePageQuery;
import com.boot.admin.domain.Role;
import com.boot.admin.domain.repository.RoleRepository;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageRequestImpl;
import com.boot.admin.domain.repository.page.PageResponse;
import com.boot.admin.domain.repository.page.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repo;
    private final RoleAssembler assembler;


    public PageReply<RoleDto> page(RolePageQuery query) {
        PageRequest pageRequest = PageRequestImpl.of(
                query.getCurrent(),
                query.getSize(),
                query.buildSpecification().toArray(new Specification[0])
        );
        PageResponse<Role> pageResponse = repo.findAll(pageRequest);

        return PageReply.of(
                pageRequest.getCurrent(), pageRequest.getSize(),
                pageResponse.getPages(), assembler.assemble(pageResponse.getContents())
        );
    }

    public RoleDto detail(Integer id) {
        return assembler.assembleForDetail(repo.findById(id));
    }

    public void offer(RoleOfferCommand command) {
        repo.save(assembler.assemble(command));
    }

    public void modify(RoleModifyCommand command) {
        repo.save(assembler.assemble(command));
    }
}
