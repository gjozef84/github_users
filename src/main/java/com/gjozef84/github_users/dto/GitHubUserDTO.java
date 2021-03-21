package com.gjozef84.github_users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUserDTO implements AbstractDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty(value = "followers")
    private long followers;
    @JsonProperty(value = "public_repos")
    private long publicRepos;
}
