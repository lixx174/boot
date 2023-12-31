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
import com.boot.admin.infra.repository.model.ResourceDo;
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
        Page<ResourceDo> page = mapper.selectPage(
                Page.of(pageRequest.getCurrent().longValue(), pageRequest.getSize().longValue()),
                analyzer.analyze(pageRequest.getSpecifications(), Wrappers.query(ResourceDo.class))
        );

        return new PageResponseImpl<>(page.getPages(), converter.convert(page.getRecords()));
    }

    @Override
    public Resource findById(Serializable id) {
        return converter.convert(mapper.selectById(id));
    }

    @Override
    public List<Resource> findByIds(Set<Integer> ids) {
        return converter.convert(mapper.selectBatchIds(ids));
    }

    @Override
    public void save(Resource resource) {
        ResourceDo resourceDo = converter.convert(resource);

        if (resource.getId() == null) {
            mapper.insert(resourceDo);
        } else {
            mapper.updateById(resourceDo);
        }
    }

    @Override
    public void removeAllById(Set<Integer> ids) {
        mapper.deleteBatchIds(ids);
    }

    @Mapper
    public interface ResourceMapper extends BaseMapper<ResourceDo> {

    }
}
