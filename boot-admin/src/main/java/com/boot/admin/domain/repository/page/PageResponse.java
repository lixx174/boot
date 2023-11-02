package com.boot.admin.domain.repository.page;

import java.util.List;

/**
 * @author Jinx
 */
public interface PageResponse<T> {
    Number getPages();

    List<T> getContents();
}
