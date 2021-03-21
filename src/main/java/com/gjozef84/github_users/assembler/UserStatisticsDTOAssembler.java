package com.gjozef84.github_users.assembler;

import com.gjozef84.github_users.dto.UserStatisticsDTO;
import com.gjozef84.github_users.model.GitHubUserRequestStatistics;
import org.springframework.stereotype.Component;

@Component
public class UserStatisticsDTOAssembler {

    public UserStatisticsDTO assembleFromDomain(GitHubUserRequestStatistics domain) {
        UserStatisticsDTO userStatisticsDTO = new UserStatisticsDTO();
        userStatisticsDTO.setLogin(domain.getLogin());
        userStatisticsDTO.setRequestCount(domain.getRequestCount());
        userStatisticsDTO.setCreatedAt(domain.getCreatedAt());
        userStatisticsDTO.setUpdatedAt(domain.getUpdatedAt());
        return userStatisticsDTO;
    }
}
