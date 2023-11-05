package com.viettel.admin.repositories;

import com.viettel.admin.auth.repository.BaseRepository;
import com.viettel.admin.models.Customer;
import com.viettel.admin.repositories.custom.CustomerCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> , CustomerCustomRepository {

    @Query(value = "select * from customers c where " +
            "( :searchText is null or c.customer_code like concat('%', :searchText, '%')" +
            " or c.trading_name like concat('%', :searchText, '%')) " +
            " and ( :customerType = 0 or c.customer_type =:customerType)" +
            " and ( :status = 0 or c.status = :status) ", nativeQuery = true)
    Page<Customer> customerSearch(
            @Param("searchText") String searchText,
            @Param("customerType") Integer customerType,
            @Param("status") Integer status,
            Pageable pageable
    );

    @Query(value = "select max(id) from customers",nativeQuery = true)
    Long getMaxId();

    List<Customer> findFirstByCustomerCodeOrCmtOrEmail(String cusCode, String cmt, String email);

    List<Customer> findByCustomerCodeInAndStatus(List<String> customerCode,String status);
    Customer findFirstByCustomerCode(String customerCode);
}