package com.gjozef84.github_users.controller;

import com.gjozef84.github_users.dto.UserDataDTO;
import com.gjozef84.github_users.dto.UserStatisticsDTO;
import com.gjozef84.github_users.service.GitHubUserRequestStatisticsService;
import com.gjozef84.github_users.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    public static final String TEST_USER_LOGIN = "fakeLogin";

    @Mock
    private UserService userService;

    @Mock
    private GitHubUserRequestStatisticsService gitHubUserRequestStatisticsService;

    private UsersController underTest;

    @BeforeEach
    void init() {
        underTest = new UsersController(userService, gitHubUserRequestStatisticsService);
    }

    @Test
    void testGetUser() {
        UserDataDTO mockUserDataDTO = Mockito.mock(UserDataDTO.class);
        Mockito.when(userService.getUser(TEST_USER_LOGIN)).thenReturn(mockUserDataDTO);
        ResponseEntity<UserDataDTO> expected = ResponseEntity.ok(mockUserDataDTO);

        ResponseEntity<UserDataDTO> actual = underTest.getUser(TEST_USER_LOGIN);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(userService).getUser(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    void testGetUserStatistics() {
        UserStatisticsDTO mockUserStatisticsDTO = Mockito.mock(UserStatisticsDTO.class);
        Mockito.when(gitHubUserRequestStatisticsService.getUserStatistics(TEST_USER_LOGIN)).thenReturn(mockUserStatisticsDTO);
        ResponseEntity<UserStatisticsDTO> expected = ResponseEntity.ok(mockUserStatisticsDTO);

        ResponseEntity<UserStatisticsDTO> actual = underTest.getUserStatistics(TEST_USER_LOGIN);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(gitHubUserRequestStatisticsService).getUserStatistics(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(gitHubUserRequestStatisticsService);
        Mockito.verifyNoInteractions(userService);
    }
}