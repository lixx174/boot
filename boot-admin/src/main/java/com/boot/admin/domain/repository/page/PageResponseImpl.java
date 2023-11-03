package com.boot.admin.domain.repository.page;

import lombok.Getter;

import java.util.List;

/**
 * @author Jinx
 */
@Getter
public class PageResponseImpl<T> implements PageResponse<T> {

    private final Number pages;
    private final List<T> contents;
    public PageResponseImpl(Number pages, List<T> contents) {
        this.pages = pages;
        this.contents = contents;
    }
}
