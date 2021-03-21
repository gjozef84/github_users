package com.gjozef84.github_users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDTO implements AbstractDTO {

    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private Long followers;
    private Long publicRepos;
    private Long calculations;
}
