package com.gjozef84.github_users.service;

import com.gjozef84.github_users.dto.UserDataDTO;

public interface UserService {
    UserDataDTO getUser(String userLogin);
}
