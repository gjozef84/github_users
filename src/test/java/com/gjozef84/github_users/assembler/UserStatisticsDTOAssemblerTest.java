package com.gjozef84.github_users.assembler;

import com.gjozef84.github_users.dto.UserStatisticsDTO;
import com.gjozef84.github_users.model.GitHubUserRequestStatistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UserStatisticsDTOAssemblerTest {

    private final UserStatisticsDTOAssembler underTest = new UserStatisticsDTOAssembler();

    @Test
    void testAssembleFromDomain() {
        LocalDateTime createdAt = LocalDateTime.now();
        GitHubUserRequestStatistics domain = new GitHubUserRequestStatistics("login", 3L);
        domain.setCreatedAt(createdAt);
        UserStatisticsDTO expected = new UserStatisticsDTO("login", 3L, createdAt, null);

        Assertions.assertEquals(expected, underTest.assembleFromDomain(domain));
    }
}