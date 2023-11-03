package com.boot.admin.infra.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.admin.domain.Role;
import com.boot.admin.domain.repository.RoleRepository;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageResponse;
import com.boot.admin.domain.repository.page.PageResponseImpl;
import com.boot.admin.infra.repository.analyzer.SpecificationAnalyzer;
import com.boot.admin.infra.repository.converter.RoleConverter;
import com.boot.admin.infra.repository.model.RoleDO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jinx
 */
@Repository
@RequiredArgsConstructor
public class DefaultRoleRepository implements RoleRepository {

    private final RoleMapper mapper;
    private final RoleConverter converter;
    private final SpecificationAnalyzer analyzer;

    @Override
    public PageResponse<Role> findAll(PageRequest pageRequest) {
        Page<RoleDO> page = mapper.selectPage(
                Page.of(pageRequest.getCurrent().longValue(), pageRequest.getSize().longValue()),
                analyzer.analyze(pageRequest.getSpecifications(), Wrappers.query(RoleDO.class))
        );

        return new PageResponseImpl<>(page.getPages(), converter.convert(page.getRecords()));
    }

    @Override
    public Role findById(Serializable id) {
        RoleDO roleDO = mapper.selectById(id);
        // FIXME check null
        Set<Integer> resourceIds = mapper.selectResourceIdsById(id);

        return converter.convert(roleDO, resourceIds);
    }

    @Override
    public void save(Role role) {
        if (role.getId() == null) {
            RoleDO roleDO = converter.convert(role);
            mapper.insert(roleDO);

            if (role.hasResource()) {
                mapper.deleteResourcesById(roleDO.getId());
                mapper.insertResources(roleDO.getId(), role.getPrimitiveResourceIds());
            }
        } else {
            // TODO update
        }
    }

    @Override
    public void removeAllById(Set<Serializable> ids) {
        mapper.deleteBatchIds(ids);
    }

    @Mapper
    public interface RoleMapper extends BaseMapper<RoleDO> {

        Set<Integer> selectResourceIdsById(Serializable id);

        void insertResources(Serializable id, Set<Integer> resourceIds);

        void deleteResourcesById(Serializable id);
    }
}
