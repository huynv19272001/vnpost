package com.viettel.admin.specifications;

import com.viettel.admin.models.CategoryService;
import com.viettel.admin.models.Partner;
import com.viettel.admin.models.PartnerService;
import com.viettel.admin.request.filter.PartnerFilter;
import com.viettel.admin.util.DataUtil;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class PartnerSpecification extends BaseSpecification<Partner, PartnerFilter> {
    @Override
    protected List<Predicate> process(CriteriaBuilder builder, Root<Partner> root, PartnerFilter filter) {
        List<Predicate> predicates = new ArrayList<>();
        if(!DataUtil.isNullOrEmpty(filter.getSearchText())){
            Predicate codeCompare = builder.like(root.get("partnerCode"), "%" + filter.getSearchText() + "%");
            Predicate nameCompare = builder.like(root.get("partnerName"), "%" + filter.getSearchText() + "%");
            predicates.add(builder.or(codeCompare,nameCompare));
        }
        if(filter.getDealerClassificationIdList() != null){
            Predicate dealerClassificationIdIn = builder.in(root.get("dealerClassificationId")).value(filter.getDealerClassificationIdList());
            predicates.add(dealerClassificationIdIn);
        }
//        if(filter.getCategoryServiceIdList() != null){
//            Join<Partner, CategoryService> joinPartService =root.join("categoryService");
//            predicates.add(builder.in(joinPartService.get("id")).value(filter.getCategoryServiceIdList()));
//        }
        return predicates;
    }
}
