package io.Email_service.Email_service.service;

import io.Email_service.Email_service.model.User;

public interface UserService {
    User saveUser(User user );
    Boolean verifyToken(String token);
}
