package com.gjozef84.github_users.controller;

import com.gjozef84.github_users.dto.UserDataDTO;
import com.gjozef84.github_users.dto.UserStatisticsDTO;
import com.gjozef84.github_users.service.GitHubUserRequestStatisticsService;
import com.gjozef84.github_users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/users/{login}")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final GitHubUserRequestStatisticsService gitHubUserRequestStatisticsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDataDTO> getUser(@PathVariable("login") String userLogin) {
        log.info("called getUser(String {}))", userLogin);

        UserDataDTO responseDTO = userService.getUser(userLogin);
        ResponseEntity<UserDataDTO> result = new ResponseEntity<>(responseDTO, HttpStatus.OK);

        log.info("about to return getUser :: result='{}'", result);
        return result;
    }

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserStatisticsDTO> getUserStatistics(@PathVariable("login") String userLogin) {
        log.info("called getUserStatistics(String {}))", userLogin);

        UserStatisticsDTO responseDTO = gitHubUserRequestStatisticsService.getUserStatistics(userLogin);
        ResponseEntity<UserStatisticsDTO> result = new ResponseEntity<>(responseDTO, HttpStatus.OK);

        log.info("about to return getUserStatistics :: result='{}'", result);
        return result;
    }
}
