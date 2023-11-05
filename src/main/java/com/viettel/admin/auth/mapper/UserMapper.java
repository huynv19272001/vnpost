package com.viettel.admin.auth.mapper;

import com.viettel.admin.auth.dto.*;
import com.viettel.admin.auth.model.User;
import com.viettel.admin.auth.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper extends EntityMapper<UserDto, User> {
  User map(UserRegistrationDto userRegistrationDto);

  User reflect(@MappingTarget User user, UserBasicInfo userBasicInfo);

  User reflect(@MappingTarget User user, UserUpdateByAdmin userUpdateByAdmin);


  List<UserDto> map(List<User> users);

  UserProfileDto map(User principal);
}
