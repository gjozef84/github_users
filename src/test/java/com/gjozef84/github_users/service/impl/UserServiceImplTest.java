package com.gjozef84.github_users.service.impl;

import com.gjozef84.github_users.assembler.GitHubUserDTOAssembler;
import com.gjozef84.github_users.dto.GitHubUserDTO;
import com.gjozef84.github_users.dto.UserDataDTO;
import com.gjozef84.github_users.exceptions.ResourceNotFoundException;
import com.gjozef84.github_users.service.GitHubUserRequestStatisticsService;
import com.gjozef84.github_users.service.UserService;
import com.gjozef84.github_users.webclient.GitHubUsersWebClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String TEST_USER_LOGIN = "fakeLogin";

    @Mock
    private GitHubUsersWebClient gitHubUsersWebClient;

    @Mock
    private GitHubUserDTOAssembler gitHubUserDTOAssembler;

    @Mock
    private GitHubUserRequestStatisticsService gitHubUserRequestStatisticsService;

    private UserService underTest;

    @BeforeEach
    void init() {
        underTest = new UserServiceImpl(gitHubUsersWebClient, gitHubUserDTOAssembler, gitHubUserRequestStatisticsService);
    }

    @Test
    void testGetUser() {
        UserDataDTO mockUserDataDTO = Mockito.mock(UserDataDTO.class);
        GitHubUserDTO mockGitHubUserDTO = Mockito.mock(GitHubUserDTO.class);
        Mockito.when(gitHubUsersWebClient.getGitHubUserByLogin(TEST_USER_LOGIN)).thenReturn(mockGitHubUserDTO);
        Mockito.when(gitHubUserDTOAssembler.assemble(mockGitHubUserDTO)).thenReturn(mockUserDataDTO);

        UserDataDTO actual = underTest.getUser(TEST_USER_LOGIN);

        Assertions.assertEquals(mockUserDataDTO, actual);
        Mockito.verify(gitHubUsersWebClient).getGitHubUserByLogin(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(gitHubUsersWebClient);
        Mockito.verify(gitHubUserDTOAssembler).assemble(Mockito.any(GitHubUserDTO.class));
        Mockito.verifyNoMoreInteractions(gitHubUserDTOAssembler);
        Mockito.verify(gitHubUserRequestStatisticsService).updateGitHubUserRequestStatistics(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(gitHubUserRequestStatisticsService);
    }

    @Test
    void testGetUser_NotFound() {
        Mockito.when(gitHubUsersWebClient.getGitHubUserByLogin(TEST_USER_LOGIN)).thenReturn(null);

        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> underTest.getUser(TEST_USER_LOGIN));

        String expectedMessage = String.format("Resource UserDataDTO with login = %s not found", TEST_USER_LOGIN);
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
        Mockito.verify(gitHubUsersWebClient).getGitHubUserByLogin(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(gitHubUsersWebClient);
        Mockito.verifyNoInteractions(gitHubUserDTOAssembler);
        Mockito.verifyNoInteractions(gitHubUserRequestStatisticsService);
    }
}