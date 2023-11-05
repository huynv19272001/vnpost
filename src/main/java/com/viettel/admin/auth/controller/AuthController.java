package com.viettel.admin.auth.controller;

import com.viettel.admin.auth.dto.*;
import com.viettel.admin.auth.dto.request.CreateUser;
import com.viettel.admin.auth.dto.request.LogOutRequest;
import com.viettel.admin.auth.dto.request.LoginRequest;
import com.viettel.admin.auth.dto.request.TokenRefreshRequest;
import com.viettel.admin.auth.dto.response.TokenRefreshResponse;
import com.viettel.admin.auth.model.RefreshToken;
import com.viettel.admin.auth.repository.UserRepository;
import com.viettel.admin.auth.sercurity.jwt.JwtTokenUtil;
import com.viettel.admin.auth.service.RefreshTokenService;
import com.viettel.admin.auth.service.UserPrincipal;
import com.viettel.admin.common.Const;
import com.viettel.admin.common.util.ValidateUtil;
import com.viettel.admin.core.enums.UserStatus;
import com.viettel.admin.core.exception.UnauthorizedException;
import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.auth.service.BlacklistTokenService;
import com.viettel.admin.auth.service.UserService;
import com.viettel.admin.auth.model.User;
import com.viettel.admin.exception.VTPostException;
import com.viettel.admin.payload.ResponseObject;
import com.viettel.admin.util.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

  @Value("${security.jwt.expiration:}")
  private Long jwtExpirationMs;

  @Value("${security.jwt.jwtExpirationMs:}")
  private Long refreshTokenDurationMs;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private BlacklistTokenService blacklistTokenService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  RefreshTokenService refreshTokenService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
      );
      String notification = "Login successfully!!";

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = jwtTokenUtil.generateJwtToken(authentication);

      UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      String role = userDetails.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.joining());

      RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

      return ResponseObject.success(new JwtResponse(
              jwt,
              refreshToken.getToken(),
              jwtExpirationMs,
              refreshTokenDurationMs,
              notification,
              role
      ));
    } catch (BadCredentialsException e) {
      throw new VTPostException(ErrorMessage.INCORRECT_USERNAME_PASSWORD,"AUTH");
    }
  }

  @PostMapping("/create-user")
  public ResponseEntity<?> createUser(@Valid @RequestBody CreateUser user) {
    return ResponseObject.success(userService.createUser(user));
  }


  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
    refreshTokenService.logoutUserId(logOutRequest);
    return ResponseObject.success();
  }

  @GetMapping(value = "/verify")
  public ResponseEntity<?> activate(@RequestParam @NotNull String email, @NotNull @RequestParam String otp) {
    userService.activate(email, otp);
    return ResponseEntity.ok(new ResponseWrapper(null));
  }
  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();
    return refreshTokenService.findByToken(requestRefreshToken)
            .map(refreshTokenService::verifyExpiration)
            .map(RefreshToken::getUserId)
            .map(userId -> {
              String token = jwtTokenUtil.generateTokenFromUsername(userService.getById(userId).orElseThrow().getFullName());
              return ResponseObject.success(new TokenRefreshResponse(token, requestRefreshToken, jwtExpirationMs, refreshTokenDurationMs));
            }).orElseGet(ResponseObject::badRequest);
  }
}
