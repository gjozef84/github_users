package com.gjozef84.github_users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GITHUB_USERS_STAT")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GitHubUserRequestStatistics extends GitHubUserEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "request_count")
    private long requestCount;

}
