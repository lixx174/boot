package com.boot.admin.domain.repository;

import com.boot.admin.domain.User;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageResponse;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jinx
 */
public interface UserRepository {

    PageResponse<User> findAll(PageRequest pageRequest);

    User findById(Serializable id);

    void save(User user);

    void removeAllById(Set<Serializable> ids);
}
