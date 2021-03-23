package com.gjozef84.github_users.service.impl;

import com.gjozef84.github_users.assembler.UserStatisticsDTOAssembler;
import com.gjozef84.github_users.dto.UserStatisticsDTO;
import com.gjozef84.github_users.model.GitHubUserRequestStatistics;
import com.gjozef84.github_users.repository.GitHubUserRequestStatisticsRepository;
import com.gjozef84.github_users.service.GitHubUserRequestStatisticsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GitHubUserRequestStatisticsServiceImplTest {

    public static final String USER_LOGIN = "fakeLogin";
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

    @Mock
    private GitHubUserRequestStatisticsRepository gitHubUserRequestStatisticsRepository;

    @Mock
    private UserStatisticsDTOAssembler userStatisticsDTOAssembler;

    private GitHubUserRequestStatisticsService underTest;

    @BeforeEach
    void init() {
        underTest = new GitHubUserRequestStatisticsServiceImpl(gitHubUserRequestStatisticsRepository, userStatisticsDTOAssembler);
    }

    @Test
    void testUpdateGitHubUserRequestStatistics() {
        GitHubUserRequestStatistics domain = new GitHubUserRequestStatistics(USER_LOGIN, 3L);
        domain.setCreatedAt(LOCAL_DATE_TIME);
        Mockito.when(gitHubUserRequestStatisticsRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.of(domain));

        underTest.updateGitHubUserRequestStatistics(USER_LOGIN);

        Assertions.assertEquals(4L, domain.getRequestCount());
        Mockito.verify(gitHubUserRequestStatisticsRepository).findByLogin(Mockito.anyString());
        Mockito.verify(gitHubUserRequestStatisticsRepository).save(Mockito.any(GitHubUserRequestStatistics.class));
        Mockito.verifyNoMoreInteractions(gitHubUserRequestStatisticsRepository);
    }

    @Test
    void testUpdateGitHubUserRequestStatistics_FirstRequest() {
        GitHubUserRequestStatistics newUserRequestStat = new GitHubUserRequestStatistics(USER_LOGIN, 0L);
        newUserRequestStat.setCreatedAt(LOCAL_DATE_TIME);
        Mockito.when(gitHubUserRequestStatisticsRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.empty());
        Mockito.when(gitHubUserRequestStatisticsRepository.save(Mockito.any(GitHubUserRequestStatistics.class))).thenReturn(newUserRequestStat);

        underTest.updateGitHubUserRequestStatistics(USER_LOGIN);

        Assertions.assertEquals(1L, newUserRequestStat.getRequestCount());
        Mockito.verify(gitHubUserRequestStatisticsRepository).findByLogin(Mockito.anyString());
        Mockito.verify(gitHubUserRequestStatisticsRepository, Mockito.times(2)).save(Mockito.any(GitHubUserRequestStatistics.class));
        Mockito.verifyNoMoreInteractions(gitHubUserRequestStatisticsRepository);
    }

    @Test
    void testGetUserStatistics() {
        GitHubUserRequestStatistics domain = new GitHubUserRequestStatistics(USER_LOGIN, 3L);
        domain.setCreatedAt(LOCAL_DATE_TIME);
        UserStatisticsDTO expected = new UserStatisticsDTO(USER_LOGIN, 3L, LOCAL_DATE_TIME, null);
        Mockito.when(gitHubUserRequestStatisticsRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.of(domain));
        Mockito.when(userStatisticsDTOAssembler.assembleFromDomain(domain)).thenReturn(expected);

        UserStatisticsDTO actual = underTest.getUserStatistics(USER_LOGIN);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(gitHubUserRequestStatisticsRepository).findByLogin(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(gitHubUserRequestStatisticsRepository);
        Mockito.verify(userStatisticsDTOAssembler).assembleFromDomain(Mockito.any(GitHubUserRequestStatistics.class));
        Mockito.verifyNoMoreInteractions(userStatisticsDTOAssembler);
    }
}