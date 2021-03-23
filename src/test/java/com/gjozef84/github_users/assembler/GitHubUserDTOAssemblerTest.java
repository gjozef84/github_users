package com.gjozef84.github_users.assembler;

import com.gjozef84.github_users.dto.GitHubUserDTO;
import com.gjozef84.github_users.dto.UserDataDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class GitHubUserDTOAssemblerTest {

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private final GitHubUserDTOAssembler underTest = new GitHubUserDTOAssembler();

    @Test
    void testAssemble() {
        GitHubUserDTO gitHubUserDTO = new GitHubUserDTO(1L, "login", "name", "type", "avatar_url", LOCAL_DATE_TIME, 1L, 1L);
        UserDataDTO expected = new UserDataDTO(1L, "login", "name", "type", "avatar_url", LOCAL_DATE_TIME, 1L, 1L, 18);

        Assertions.assertEquals(expected, underTest.assemble(gitHubUserDTO));
    }

    @Test
    void testAssemble_() {
        GitHubUserDTO gitHubUserDTO = new GitHubUserDTO(1L, "login", "name", "type", "avatar_url", LOCAL_DATE_TIME, 10L, 2L);
        UserDataDTO expected = new UserDataDTO(1L, "login", "name", "type", "avatar_url", LOCAL_DATE_TIME, 10L, 2L, 2.4);

        Assertions.assertEquals(expected, underTest.assemble(gitHubUserDTO));
    }

    @Test
    void testAssemble_whenNotFollowers() {
        GitHubUserDTO gitHubUserDTO = new GitHubUserDTO(1L, "login", "name", "type", "avatar_url", LOCAL_DATE_TIME, 0L, 1L);
        UserDataDTO expected = new UserDataDTO(1L, "login", "name", "type", "avatar_url", LOCAL_DATE_TIME, 0L, 1L, 0);

        Assertions.assertEquals(expected, underTest.assemble(gitHubUserDTO));
    }
}