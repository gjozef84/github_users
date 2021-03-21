package com.gjozef84.github_users.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@ToString(callSuper = true)
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class GitHubUserEntity implements GitHubUserDomain, Persistable<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Override
    public boolean isNew() {
        return null == getId();
    }
}

