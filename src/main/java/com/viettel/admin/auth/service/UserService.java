package com.viettel.admin.auth.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.viettel.admin.auth.dto.*;
import com.viettel.admin.auth.dto.request.CreateUser;
import com.viettel.admin.auth.model.Role;
import com.viettel.admin.auth.model.User;
import com.viettel.admin.auth.model.UserRole;
import com.viettel.admin.auth.repository.RoleRepository;
import com.viettel.admin.auth.repository.UserRepository;
import com.viettel.admin.auth.repository.UserRoleRepository;
import com.viettel.admin.common.Const;
import com.viettel.admin.common.util.ValidateUtil;
import com.viettel.admin.core.enums.UserStatus;
import com.viettel.admin.core.model.Counter;
import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.auth.mapper.UserMapper;
import com.viettel.admin.exception.VTPostException;
import com.viettel.admin.util.ErrorMessage;
import com.viettel.admin.util.ListResult;
import com.viettel.admin.util.PageableUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserRoleRepository userRoleRepository;

  private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Autowired
  private RoleRepository roleRepository;

  private static final Integer EXPIRE_MIN = 5;
  private static LoadingCache<String, OtpVerify> otpCache;

  public UserService() {
    super();
    otpCache = CacheBuilder.newBuilder()
        .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
        .build(new CacheLoader<String, OtpVerify>() {
          @Override
          public OtpVerify load(String s) throws Exception {
            return null;
          }
        });
  }
  public Optional<User> getById(Long id) {
    return userRepository.findById(id);
  }

  @Transactional
  public ResponseWrapper changePassword(String currentClearTextPassword, String newPassword, String confirmNewPass) {
    Assert.isTrue(newPassword.equals(confirmNewPass), Const.MESSAGE_CODE.CONFIRMING_PASS_NOT_MATCH);
    ResponseWrapper result = new ResponseWrapper();
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserProfileDto currenUserLogin = userMapper.map((User) principal);
    User changePassUser = userRepository.findByIdAndDeleted(currenUserLogin.getId(), false).orElse(null);
    Assert.notNull(changePassUser, Const.MESSAGE_CODE.USER_NOT_FOUND);
    String currentEncryptedPassword = changePassUser.getPassword();
    if (!new BCryptPasswordEncoder().matches(currentClearTextPassword, currentEncryptedPassword)) {
      throw new IllegalArgumentException(Const.MESSAGE_CODE.WRONG_PASSWORD);
    }
    changePassUser.setPassword(new BCryptPasswordEncoder().encode(newPassword));
    userRepository.save(changePassUser);
    result.setData(null);
    result.setMessageCode(Const.MESSAGE_CODE.OK);
    result.setSuccess(true);
    result.setHttpStatusCode(String.valueOf(HttpStatus.OK));
    return result;
  }

  @Transactional(readOnly = true)
  public Page<UserDto> findAll(Pageable pageable) {
    return userRepository.findAll(pageable)
        .map(userMapper::toDto);
  }

  public ResponseWrapper findOne(Long id) {
    ResponseWrapper result = new ResponseWrapper();
    User user = userRepository.findById(id).orElse(null);
    if (user == null) {
      result.setData(null);
      result.setMessageCode(Const.MESSAGE_CODE.USER_NOT_FOUND);
      result.setSuccess(false);
      result.setHttpStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
    } else {
      result.setData(userMapper.toDto(user));
      result.setMessageCode(Const.MESSAGE_CODE.OK);
      result.setSuccess(true);
      result.setHttpStatusCode(String.valueOf(HttpStatus.OK));
    }
    return result;
  }

  public ListResult<User> filterUser(String userName, String email, String phoneNumber, int page, int size, String orderBy, boolean desc) {
    if (StringUtils.isNotBlank(userName)) {
      userName = userName.replaceAll("\\s+", " ").trim();
    }
    if (StringUtils.isNotBlank(email)) {
      email = email.replaceAll("\\s+", " ").trim();
    }
    if (StringUtils.isNotBlank(phoneNumber)) {
      phoneNumber = phoneNumber.replaceAll("\\s+", " ").trim();
    }
    return ListResult.from(userRepository.filterUser(userName, email, phoneNumber, PageableUtils.pageable(page, size, orderBy, desc)));
  }

    private List<UserProfileDto> mapUsrInCharge(List<UserProfileDto> searchResults) {
    ArrayList<UserProfileDto> listEmployee = userRepository
        .getListEmployee().stream()
        .map(userMapper::map).collect(Collectors.toCollection(ArrayList::new));
    listEmployee.removeAll(listEmployee.stream().filter(employee -> employee.getUserCode().startsWith("VSHIP")).collect(Collectors.toList()));
    searchResults.forEach(i -> {
      if (i.getPersonInCharge() != null) {
        Integer ursId = (Integer) i.getPersonInCharge();
        if(ursId != -1) {
          listEmployee.forEach(emp -> {
            if(listEmployee.stream().map(item -> Integer.valueOf(item.getId().toString())).collect(Collectors.toList()).contains(ursId)){
              if (Objects.equals(Math.toIntExact(emp.getId()), ursId)) {
                i.setPersonInCharge(new PersonInCharge(ursId, emp.getFullName()));
              }
            } else {
              i.setPersonInCharge(emp.getFullName());

            }
          });
        } else {
          i.setPersonInCharge(null);
        }
      }
    });
    return searchResults;
  }


  public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findFirstByFullName(username);
    Set<GrantedAuthority> authorities = new HashSet<>();
    if (ObjectUtils.isEmpty(user)) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "ko tồn tại trên hệ thống");
    }
    List<Role> roles = roleRepository.listRoleByUser(user.getId());
    roles.forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    });

    return UserPrincipal.build(user,authorities);
  }

  public CreateUser createUser(CreateUser user) {
//    user.getRoles().forEach(role -> {
//      String roleId = UUID.randomUUID().toString();
//      role.setRoleId(roleId);
//      roleRepository.save(role);
//    });
    List<UserRole> userRoles = new ArrayList<>();
    String userId = UUID.randomUUID().toString();
//    user.getRoles().forEach(role -> {
//      String roleUser = role.getRoleName();
//      String roleId = UUID.randomUUID().toString();
//      role.setRoleId(roleId);
//      roleRepository.save(role);
//      UserRole userRole = new UserRole();
//      userRole.setRoleId(roleId);
//      userRole.setUserId(userId);
//      userRoles.add(userRole);
//    });
    user.getUser().setPassword(passwordEncoder.encode(user.getUser().getPassword()));
    userRepository.save(user.getUser());
    user.getUser().setUserId(userId);
    userRoleRepository.saveAll(userRoles);
    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = loadUserByUser(username);
    Set<GrantedAuthority> authorities = new HashSet<>();
    if (ObjectUtils.isEmpty(user)) {
      throw new VTPostException(ErrorMessage.INCORRECT_USERNAME_PASSWORD,"AUTH");
    }
    List<Role> roles = roleRepository.listRoleByUser(user.getId());
    roles.forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    });

    return UserPrincipal.build(user,authorities);
  }

  public User loadUserByUser(String username) throws UsernameNotFoundException {
    if (ValidateUtil.regexValidation(username, Const.VALIDATE_INPUT.regexEmail))
      return findByEmail(username);
    return findByMobile(username);
  }

  private User findByMobile(String mobi) {
    User user = userRepository.findByMobileAndDeleted(mobi, false);
    if(!ObjectUtils.isEmpty(user)){
      return user;
    }
    return null;
  }

  private User findByEmail(String email) {
    User user = userRepository.findByEmailAndDeleted(email, false);
    if(!ObjectUtils.isEmpty(user)){
      return user;
    }
    return null;
  }

  private boolean checkIsEmailExisted(String email) {
    User dbUser = userRepository.findByEmailIgnoreCaseAndDeleted(email.trim(), false).orElse(null);
    return dbUser != null;
  }

  public void activate(String email, String otp) {
    Assert.isTrue(ValidateUtil.regexValidation(email, Const.VALIDATE_INPUT.regexEmail), Const.MESSAGE_CODE.INVALID_EMAIL);
    OtpVerify otpVerify = otpCache.getIfPresent(email);
    Assert.notNull(otpVerify, Const.MESSAGE_CODE.OTP_EXPIRED);
    Assert.isTrue(otpVerify.getRetryCount().intValue() < 5, Const.MESSAGE_CODE.OTP_5_TIMES);
    if (!otpVerify.getOtp().equals(otp)) {
      if (otpVerify.getRetryCount().intValue() < 5) {
        otpCache.invalidate(email);
        otpCache.put(email, new OtpVerify(otpVerify.getOtp(), otpVerify.getRetryCount() + 1));
        throw new IllegalArgumentException(Const.MESSAGE_CODE.OTP_INCORRECT);
      } else {
        throw new IllegalArgumentException(Const.MESSAGE_CODE.OTP_5_TIMES);
      }
    }
  }

}
