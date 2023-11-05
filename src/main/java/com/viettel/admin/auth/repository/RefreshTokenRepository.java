package com.viettel.admin.auth.repository;

import com.viettel.admin.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
//    Optional<RefreshToken> findById(Integer id);

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUserId(Integer userId);
    List<RefreshToken> findByUserIdAndStatus(Long userId,boolean status);
}