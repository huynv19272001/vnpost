package com.viettel.admin.specifications;

import com.viettel.admin.request.filter.BaseFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSpecification<T, F extends BaseFilter> {
    protected abstract List<Predicate> process(CriteriaBuilder builder, Root<T> root, F filter);

    public Specification<T> getAll(F filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //custom predicate here
            predicates.addAll(process(criteriaBuilder, root, filter));

            //prevent load all soft deleted already record
            Predicate isNotDeletedPredicate = criteriaBuilder.equal(root.get("deleted"), false);
            predicates.add(isNotDeletedPredicate);

//            if(filter.getLanguageType() != null) {
//
//                Predicate languageType = criteriaBuilder.equal(root.get("languageType"), filter.getLanguageType());
//                predicates.add(languageType);
//            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
