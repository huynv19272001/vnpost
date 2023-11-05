package com.viettel.admin.auth.mapper;

import com.viettel.admin.auth.model.BlacklistToken;
import com.viettel.admin.auth.dto.BlacklistTokenDto;
import org.mapstruct.Mapper;

@Mapper
public interface BlacklistTokenMapper {

  BlacklistToken map(BlacklistTokenDto dto);

}
