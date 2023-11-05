package com.viettel.admin.auth.repository;

import com.viettel.admin.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Set<Role> findByIdInAndDeletedAndStatus(List<Long> roleIds, boolean deleted, Integer roleStatus);

  @Query(value = "select roles.* from users join user_role on users.id = user_role.user_id\n" +
          "    join roles on roles.id = user_role.role_id where users.id = :userId",
          nativeQuery = true)
  List<Role> listRoleByUser(@Param("userId") Long userId);

  List<Role> findByIdIn(List<Long> roleIds);
}
