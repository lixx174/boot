package com.boot.admin.infra.repository.analyzer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.admin.domain.repository.page.Specification;
import com.boot.admin.domain.repository.page.Specification.Operator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Jinx
 */
@Component
public class SpecificationAnalyzer {

    public <T> QueryWrapper<T> analyze(Set<Specification> specifications, QueryWrapper<T> wrapper) {
        for (Specification specification : specifications) {
            MybatisOperator.loadByOperator(specification.getOperator())
                    .analyze(specification, wrapper);
        }

        return wrapper;
    }


    @AllArgsConstructor
    public enum MybatisOperator {
        EQ(Operator.EQUALS) {
            @Override
            void analyze(Specification specification, QueryWrapper<?> wrapper) {
                wrapper.eq(specification.getColumn(), specification.getValue());
            }
        },
        LIKE(Operator.LIKE) {
            @Override
            void analyze(Specification specification, QueryWrapper<?> wrapper) {
                wrapper.like(specification.getColumn(), specification.getValue());
            }
        };

        private static final MybatisOperator[] VALUES = values();
        private final Operator operator;

        static MybatisOperator loadByOperator(Operator operator) {
            for (MybatisOperator mybatisOperator : VALUES) {
                if (operator == mybatisOperator.operator) {
                    return mybatisOperator;
                }
            }

            throw new IllegalArgumentException("illegal operator [%s]".formatted(operator));
        }

        abstract void analyze(Specification specification, QueryWrapper<?> wrapper);
    }
}
