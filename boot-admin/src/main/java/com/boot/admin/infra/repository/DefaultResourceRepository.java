package com.boot.admin.infra.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.admin.domain.Resource;
import com.boot.admin.domain.repository.ResourceRepository;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageResponse;
import com.boot.admin.domain.repository.page.PageResponseImpl;
import com.boot.admin.infra.repository.analyzer.SpecificationAnalyzer;
import com.boot.admin.infra.repository.converter.ResourceConverter;
import com.boot.admin.infra.repository.model.ResourceDO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Jinx
 */
@Repository
@RequiredArgsConstructor
public class DefaultResourceRepository implements ResourceRepository {

    private final ResourceMapper mapper;
    private final ResourceConverter converter;
    private final SpecificationAnalyzer analyzer;

    @Override
    public List<Resource> findAll() {
        return converter.convert(mapper.selectList(Wrappers.emptyWrapper()));
    }

    @Override
    public PageResponse<Resource> findAll(PageRequest pageRequest) {
        Page<ResourceDO> page = mapper.selectPage(
                Page.of(pageRequest.getCurrent().longValue(), pageRequest.getSize().longValue()),
                analyzer.analyze(pageRequest.getSpecifications(), Wrappers.query(ResourceDO.class))
        );

        return new PageResponseImpl<>(page.getPages(), converter.convert(page.getRecords()));
    }

    @Override
    public Resource findById(Serializable id) {
        return null;
    }

    @Override
    public void save(Resource resource) {

    }

    @Override
    public void removeAllById(Set<Serializable> ids) {
        mapper.deleteBatchIds(ids);
    }

    @Mapper
    public interface ResourceMapper extends BaseMapper<ResourceDO> {

    }
}
