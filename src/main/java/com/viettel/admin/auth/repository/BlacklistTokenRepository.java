package com.viettel.admin.auth.repository;

import com.viettel.admin.auth.model.BlacklistToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BlacklistTokenRepository
    extends JpaRepository<BlacklistToken, Long>, JpaSpecificationExecutor<BlacklistToken> {

  Optional<BlacklistToken> findByToken(String token);

  @Transactional
  void deleteAllByCreatedDateBefore(Date beforeDate);

}
