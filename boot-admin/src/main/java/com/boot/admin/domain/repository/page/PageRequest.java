package com.boot.admin.domain.repository.page;

import com.boot.admin.domain.Specification;

import java.util.Set;

/**
 * @author jinx
 */
public interface PageRequest {
    Number getCurrent();

    Number getSize();

    Set<Specification> getSpecifications();
}
