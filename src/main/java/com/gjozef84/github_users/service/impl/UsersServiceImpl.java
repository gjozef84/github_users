package com.gjozef84.github_users.service.impl;

import com.gjozef84.github_users.assembler.GitHubUserDTOAssembler;
import com.gjozef84.github_users.dto.UserDataDTO;
import com.gjozef84.github_users.exceptions.ResourceNotFoundException;
import com.gjozef84.github_users.service.GitHubUserRequestStatisticsService;
import com.gjozef84.github_users.service.UsersService;
import com.gjozef84.github_users.webclient.GitHubUsersClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final GitHubUsersClient gitHubUsersClient;
    private final GitHubUserDTOAssembler gitHubUserDTOAssembler;
    private final GitHubUserRequestStatisticsService gitHubUserRequestStatisticsService;

    @Override
    public UserDataDTO getUser(String userLogin) {
        log.debug("calling getUser(String {})", userLogin);
        UserDataDTO userDataDTO = Optional.ofNullable(gitHubUsersClient.getGitHubUser(userLogin))
            .map(gitHubUserDTOAssembler::assemble)
            .orElseThrow(() -> new ResourceNotFoundException(UserDataDTO.class, "login", userLogin));

        gitHubUserRequestStatisticsService.updateGitHubUserRequestStatistics(userLogin);
        log.debug("getUser(String {}) returning {}", userLogin, userDataDTO);
        return userDataDTO;
    }
}
