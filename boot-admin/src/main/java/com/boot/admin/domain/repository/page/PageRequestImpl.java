package com.boot.admin.domain.repository.page;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * @author Jinx
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageRequestImpl implements PageRequest {

    private final Number current;
    private final Number size;
    // FIXME 目前默认所有的条件都是and
    private final Set<Specification> specifications;


    public static PageRequestImpl of(Number current, Number size, Specification... specifications) {
        return new PageRequestImpl(current, size, Set.of(specifications));
    }
}
