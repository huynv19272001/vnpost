package com.viettel.admin.repositories;

import com.viettel.admin.models.Department;
import com.viettel.admin.models.RequestQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRequestRepository extends JpaRepository<RequestQuote, Long> {


}