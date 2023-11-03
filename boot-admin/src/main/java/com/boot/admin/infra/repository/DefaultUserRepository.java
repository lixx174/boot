package com.boot.admin.infra.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.admin.domain.User;
import com.boot.admin.domain.repository.UserRepository;
import com.boot.admin.domain.repository.page.PageRequest;
import com.boot.admin.domain.repository.page.PageResponse;
import com.boot.admin.domain.repository.page.PageResponseImpl;
import com.boot.admin.infra.repository.analyzer.SpecificationAnalyzer;
import com.boot.admin.infra.repository.converter.UserConverter;
import com.boot.admin.infra.repository.model.UserDo;
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
public class DefaultUserRepository implements UserRepository {

    private final UserMapper mapper;
    private final UserConverter converter;
    private final SpecificationAnalyzer analyzer;

    @Override
    public PageResponse<User> findAll(PageRequest pageRequest) {
        Page<UserDo> page = mapper.selectPage(
                Page.of(pageRequest.getCurrent().longValue(), pageRequest.getSize().longValue()),
                analyzer.analyze(pageRequest.getSpecifications(), Wrappers.query(UserDo.class))
        );

        return new PageResponseImpl<>(page.getPages(), converter.convert(page.getRecords()));
    }

    @Override
    public User findById(Serializable id) {
        return converter.convert(mapper.selectById(id));
    }

    @Override
    public void save(User user) {
        UserDo userDo = converter.convert(user);

        if (user.getId() == null) {
            mapper.insert(userDo);
        } else {
            mapper.updateById(userDo);
        }
    }

    @Override
    public void removeAllById(Set<Serializable> ids) {
        mapper.deleteBatchIds(ids);
    }

    @Mapper
    public interface UserMapper extends BaseMapper<UserDo> {

    }
}
