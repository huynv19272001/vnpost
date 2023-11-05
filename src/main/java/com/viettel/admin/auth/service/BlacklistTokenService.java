package com.viettel.admin.auth.service;

import com.viettel.admin.auth.model.BlacklistToken;
import com.viettel.admin.auth.repository.BlacklistTokenRepository;
import com.viettel.admin.core.config.MessageTemplate;
import com.viettel.admin.auth.dto.BlacklistTokenDto;
import com.viettel.admin.auth.mapper.BlacklistTokenMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BlacklistTokenService {

  @Autowired
  private BlacklistTokenRepository blacklistTokenRepository;

  @Autowired
  private MessageTemplate messageTemplate;

  private final BlacklistTokenMapper blacklistTokenMapper = Mappers.getMapper(BlacklistTokenMapper.class);

  public Optional<BlacklistToken> findByToken(String token) {
    return blacklistTokenRepository.findByToken(token);
  }

  public BlacklistToken create(BlacklistToken blacklistToken) {
    return blacklistTokenRepository.save(blacklistToken);
  }

  public void deleteAllByDateBefore(Date beforeDate) {
    blacklistTokenRepository.deleteAllByCreatedDateBefore(beforeDate);
  }

}
