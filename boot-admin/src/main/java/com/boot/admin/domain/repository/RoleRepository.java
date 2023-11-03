package com.boot.admin.domain.repository;

import com.boot.admin.domain.Role;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageResponse;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jinx
 */
public interface RoleRepository {

    PageResponse<Role> findAll(PageRequest pageRequest);

    Role findById(Serializable id);

    void save(Role resource);

    void removeAllById(Set<Serializable> ids);
}
