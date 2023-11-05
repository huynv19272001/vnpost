package com.viettel.admin.repositories;

import com.viettel.admin.models.Partner;
import com.viettel.admin.models.RequestQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestQuoteRepository extends JpaRepository<RequestQuote, Long> {


}