package com.boot.admin.infra.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.admin.domain.Resource;
import com.boot.admin.domain.Role;
import com.boot.admin.domain.repository.RoleRepository;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageResponse;
import com.boot.admin.domain.repository.page.PageResponseImpl;
import com.boot.admin.infra.repository.analyzer.SpecificationAnalyzer;
import com.boot.admin.infra.repository.converter.RoleConverter;
import com.boot.admin.infra.repository.model.RoleDo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

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
        Page<RoleDo> page = mapper.selectPage(
                Page.of(pageRequest.getCurrent().longValue(), pageRequest.getSize().longValue()),
                analyzer.analyze(pageRequest.getSpecifications(), Wrappers.query(RoleDo.class))
        );

        return new PageResponseImpl<>(page.getPages(), converter.convert(page.getRecords()));
    }

    @Override
    public Role findById(Serializable id) {
        RoleDo roleDO = mapper.selectById(id);
        Set<Integer> resourceIds = mapper.selectResourceIdsById(id);

        return converter.convert(roleDO, resourceIds);
    }

    @Override
    public void save(Role role) {
        RoleDo roleDO = converter.convert(role);

        if (role.getId() == null) {
            mapper.insert(roleDO);
        } else {
            mapper.updateById(roleDO);
        }

        if (role.hasResource()) {
            mapper.deleteResourcesById(roleDO.getId());
            mapper.insertResources(
                    roleDO.getId(),
                    role.getResources().stream().map(Resource::getId).collect(Collectors.toSet())
            );
        }
    }

    @Override
    public void removeAllById(Set<Serializable> ids) {
        mapper.deleteBatchIds(ids);
    }

    /**
     * TODO 内部类 MybatisX插件无法跳转
     */
    @Mapper
    public interface RoleMapper extends BaseMapper<RoleDo> {

        Set<Integer> selectResourceIdsById(Serializable id);

        void insertResources(Serializable id, Set<Integer> resourceIds);

        void deleteResourcesById(Serializable id);
    }
}
