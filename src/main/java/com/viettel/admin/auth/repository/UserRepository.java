package com.viettel.admin.auth.repository;

import com.viettel.admin.auth.repository.custom.UserCustomRepository;
import com.viettel.admin.core.enums.UserStatus;
import com.viettel.admin.auth.model.Role;
import com.viettel.admin.auth.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long>, UserCustomRepository {



  User findByEmailAndDeleted(String email, boolean deleted);

  User findByMobileAndDeleted(String mobile, boolean deleted);

  User findFirstByFullName(String userName);

  @Query(value = "SELECT * FROM users u " +
      "WHERE " +
      "(LOWER(u.full_name) LIKE %:query% " +
      "OR LOWER(u.email) LIKE %:query% " +
      "OR LOWER(u.mobile) LIKE %:query% " +
      "OR LOWER(u.user_code) LIKE %:query%) " +
      "AND (:status is null OR u.status LIKE :status) " +
      "AND (:feeStatus is null OR u.fee_status = :feeStatus) " +
      "AND u.deleted = false AND (:personInCharge is null OR u.person_in_charge = :personInCharge) AND u.id IN (SELECT ur.user_id FROM user_role ur WHERE ur.role_id = 3)",nativeQuery = true)
  Page<User> findUsers(
      @Param("query") String query,
      @Param("status") String status,
      @Param("personInCharge") Integer personInCharge,
      @Param(("feeStatus")) Integer feeStatus,
      Pageable pageable);

  @Query(value = "SELECT * from employee a WHERE a.id IN (SELECT user_id FROM user_role WHERE role_id != 1) and a.status = 'ACTIVE'", nativeQuery = true)
  List<User> getListEmployee();

  Optional<User> findByEmailIgnoreCaseAndDeleted(String email, boolean deleted);

  Optional<User> findByIdAndDeleted(Long id, boolean deleted);

  boolean existsByMobileAndDeleted(String mobile, boolean deleted);


  @Query(value = "SELECT * FROM employee a " +
      "WHERE " +
      "(LOWER(a.full_name) LIKE %:query% " +
      "OR LOWER(a.email) LIKE %:query% " +
      "OR LOWER(a.mobile) LIKE %:query% " +
      "OR LOWER(a.user_code) LIKE %:query%) " +
      "AND (:status is null OR a.status LIKE :status) " +
      "AND a.deleted = false AND (:personInCharge is null OR a.person_in_charge = :personInCharge) AND a.id IN (SELECT ar.user_id FROM user_role ar WHERE ar.role_id != 1)" +
      "AND (:role is null OR a.id IN (SELECT ar.user_id FROM user_role ar WHERE ar.role_id = :role))",nativeQuery = true)
  Page<User> findEmployee(
      @Param("query") String query,
      @Param("status") String status,
      @Param("personInCharge") Integer personInCharge,
      @Param("role") Long role,
      Pageable pageable);
}
