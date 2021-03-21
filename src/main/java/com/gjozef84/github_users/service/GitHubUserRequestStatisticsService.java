package com.gjozef84.github_users.service;

import com.gjozef84.github_users.dto.UserStatisticsDTO;

public interface GitHubUserRequestStatisticsService {
    void updateGitHubUserRequestStatistics(String userLogin);

    UserStatisticsDTO getUserStatistics(String userLogin);
}
