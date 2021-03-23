package com.gjozef84.github_users.assembler;

import com.gjozef84.github_users.dto.GitHubUserDTO;
import com.gjozef84.github_users.dto.UserDataDTO;
import org.springframework.stereotype.Component;

@Component
public class GitHubUserDTOAssembler {

    public UserDataDTO assemble(GitHubUserDTO gitHubUserDTO) {
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setId(gitHubUserDTO.getId());
        userDataDTO.setLogin(gitHubUserDTO.getLogin());
        userDataDTO.setName(gitHubUserDTO.getName());
        userDataDTO.setType(gitHubUserDTO.getType());
        userDataDTO.setAvatarUrl(gitHubUserDTO.getAvatarUrl());
        userDataDTO.setCreatedAt(gitHubUserDTO.getCreatedAt());
        userDataDTO.setFollowers(gitHubUserDTO.getFollowers());
        userDataDTO.setPublicRepos(gitHubUserDTO.getPublicRepos());
        userDataDTO.setCalculations(calculateUserData(gitHubUserDTO.getFollowers(), gitHubUserDTO.getPublicRepos()));
        return userDataDTO;
    }

    private double calculateUserData(long followers, long publicRepos) {
        if (followers == 0) {
            return 0.00;
        } else {
            return (double) 6 / followers * (2 + publicRepos);
        }
    }
}
