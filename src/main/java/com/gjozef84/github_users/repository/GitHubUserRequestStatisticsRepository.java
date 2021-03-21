package com.gjozef84.github_users.repository;

import com.gjozef84.github_users.model.GitHubUserRequestStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GitHubUserRequestStatisticsRepository extends JpaRepository<GitHubUserRequestStatistics, Long> {
    Optional<GitHubUserRequestStatistics> findByLogin(String userLogin);
}
