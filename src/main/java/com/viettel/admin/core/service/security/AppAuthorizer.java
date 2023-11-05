package com.viettel.admin.core.service.security;

import org.springframework.security.core.Authentication;

public interface AppAuthorizer {

    boolean authorize(Authentication authentication);
}
