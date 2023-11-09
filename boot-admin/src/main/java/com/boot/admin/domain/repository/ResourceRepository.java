package com.boot.admin.domain.repository;

import com.boot.admin.domain.Resource;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Jinx
 */
public interface ResourceRepository {

    List<Resource> findAll();

    PageResponse<Resource> findAll(PageRequest pageRequest);

    Resource findById(Serializable id);

    List<Resource> findByIds(Set<Integer> ids);

    void save(Resource resource);

    void removeAllById(Set<Integer> ids);
}
