package com.viettel.admin.core.service.security;

import com.viettel.admin.auth.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("vShipAuthorizer")
@Slf4j
public class AppAuthorizerImpl implements AppAuthorizer {

  private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public boolean authorize(Authentication authentication) {
      return true;
//      HttpServletRequest request =
//              ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
//                      .getRequest();
//      String path = request.getContextPath() + request.getServletPath();
//      String method = request.getMethod();
//      if (DataUtil.isNullOrEmpty(path) || DataUtil.isNullOrEmpty(method)){
//        return false;
//      }
//      path = path.trim();
//      method = method.trim();
//      long userId;
//      try {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserProfileDto resultDTO = userMapper.map((User) principal);
//        if (resultDTO == null || resultDTO.getId() == null){
//          userId = -1;
//        }else{
//          userId = resultDTO.getId();
//        }
//      }catch (Exception e){
//        userId = -1;
//        log.error(e.getMessage());
//      }
//      List<PermissionPathUrl> permissionPathUrls = permissionPathUrlRepository.getPermissionForUser(userId, path, method);
//      return permissionPathUrls.size() > 0;
    }
}
