package com.gjozef84.github_users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatisticsDTO implements AbstractDTO {
    private String login;
    private long requestCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
