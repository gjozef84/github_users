package com.gjozef84.github_users.service.impl;

import com.gjozef84.github_users.assembler.UserStatisticsDTOAssembler;
import com.gjozef84.github_users.dto.UserStatisticsDTO;
import com.gjozef84.github_users.exceptions.ResourceNotFoundException;
import com.gjozef84.github_users.model.GitHubUserRequestStatistics;
import com.gjozef84.github_users.repository.GitHubUserRequestStatisticsRepository;
import com.gjozef84.github_users.service.GitHubUserRequestStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class GitHubUserRequestStatisticsServiceImpl implements GitHubUserRequestStatisticsService {

    private final GitHubUserRequestStatisticsRepository gitHubUserRequestStatisticsRepository;
    private final UserStatisticsDTOAssembler userStatisticsDTOAssembler;

    @Override
    public void updateGitHubUserRequestStatistics(String userLogin) {
        log.info("updateGitHubUserRequestStatistics(String {})", userLogin);
        final GitHubUserRequestStatistics gitHubUserRequestStatistics =
            gitHubUserRequestStatisticsRepository.findByLogin(userLogin).orElseGet(() -> createNewGitHubUserRequestStatistics(userLogin));
        gitHubUserRequestStatistics.setRequestCount(gitHubUserRequestStatistics.getRequestCount() + 1);
        gitHubUserRequestStatistics.setUpdatedAt(LocalDateTime.now());
        gitHubUserRequestStatisticsRepository.save(gitHubUserRequestStatistics);
        log.info("successfully updated request count {} for user = {}", gitHubUserRequestStatistics.getRequestCount(), userLogin);
    }

    @Override
    public UserStatisticsDTO getUserStatistics(String userLogin) {
        log.info("getUserStatistics(String {}", userLogin);
        final GitHubUserRequestStatistics gitHubUserRequestStatistics = fetchGitHubUserRequestStatistics(userLogin);
        final UserStatisticsDTO userStatisticsDTO = userStatisticsDTOAssembler.assembleFromDomain(gitHubUserRequestStatistics);
        log.info("getUserStatistics(String {}) returning {}", userLogin, userStatisticsDTO);
        return userStatisticsDTO;
    }

    private GitHubUserRequestStatistics fetchGitHubUserRequestStatistics(String userLogin) {
        return gitHubUserRequestStatisticsRepository.findByLogin(userLogin)
            .orElseThrow(() -> new ResourceNotFoundException(GitHubUserRequestStatistics.class, "login", userLogin));
    }

    private GitHubUserRequestStatistics createNewGitHubUserRequestStatistics(String userLogin) {
        log.debug("createNewGitHubUserRequestStatistics(String {})", userLogin);
        GitHubUserRequestStatistics newUserRequestStatistics = new GitHubUserRequestStatistics();
        newUserRequestStatistics.setLogin(userLogin);
        newUserRequestStatistics.setCreatedAt(LocalDateTime.now());
        return gitHubUserRequestStatisticsRepository.save(newUserRequestStatistics);
    }
}
