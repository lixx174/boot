package com.boot.admin.application.service;

import com.boot.admin.application.PageReply;
import com.boot.admin.application.assembler.UserAssembler;
import com.boot.admin.application.dto.UserDto;
import com.boot.admin.application.dto.command.UserModifyCommand;
import com.boot.admin.application.dto.command.UserOfferCommand;
import com.boot.admin.application.dto.command.UserPasswordModifyCommand;
import com.boot.admin.application.dto.query.UserPageQuery;
import com.boot.admin.domain.Specification;
import com.boot.admin.domain.User;
import com.boot.admin.domain.primitive.Password;
import com.boot.admin.domain.repository.UserRepository;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageRequestImpl;
import com.boot.admin.domain.repository.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final UserAssembler assembler;


    public PageReply<UserDto> page(UserPageQuery query) {
        PageRequest pageRequest = PageRequestImpl.of(
                query.getCurrent(),
                query.getSize(),
                query.buildSpecification().toArray(new Specification[0])
        );
        PageResponse<User> pageResponse = repo.findAll(pageRequest);

        return PageReply.of(
                pageRequest.getCurrent(), pageRequest.getSize(),
                pageResponse.getPages(), assembler.assemble(pageResponse.getContents())
        );
    }

    public UserDto detail(Serializable id) {
        return assembler.assemble(repo.findById(id));
    }

    public void offer(UserOfferCommand command) {
        repo.save(assembler.assemble(command));
    }

    public void modify(UserModifyCommand command) {
        repo.save(assembler.assemble(command));
    }

    public void remove(Set<Serializable> ids) {
        repo.removeAllById(ids);
    }

    public void passwordModify(UserPasswordModifyCommand command) {
        User user = repo.findById(command.getId());

        user.modifyPassword(
                new Password(command.getOriginalPassword()),
                new Password(command.getLatestPassword())
        );

        repo.save(user);
    }

    public UserDto profile() {
        return null;
    }
}
