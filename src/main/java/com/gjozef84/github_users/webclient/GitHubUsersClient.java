package com.gjozef84.github_users.webclient;

import com.gjozef84.github_users.dto.GitHubUserDTO;
import com.gjozef84.github_users.dto.UserDataDTO;
import com.gjozef84.github_users.exceptions.ApplicationException;
import com.gjozef84.github_users.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class GitHubUsersClient {

    private final String scheme;
    private final String host;
    private final String path;
    private final RestTemplate restTemplate;

    public GitHubUsersClient(@Value("${github-users.webclient.scheme}") String scheme,
                             @Value("${github-users.webclient.host}") String host,
                             @Value("${github-users.webclient.path}") String path) {
        this.scheme = scheme;
        this.host = host;
        this.path = path;
        this.restTemplate = new RestTemplate();
    }

    public GitHubUserDTO getGitHubUser(String login) {
        Map<String, Object> params = new HashMap<>();
        params.put("login", login);
        String uri = createURI(params);

        try {
            ResponseEntity<GitHubUserDTO> searchResult = callRestMethod(uri, HttpMethod.GET, GitHubUserDTO.class);
            return searchResult.getBody();
        } catch (final HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException(UserDataDTO.class, "login", login);
        } catch (final RestClientException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }

    private <T> ResponseEntity<T> callRestMethod(String uri, HttpMethod httpMethod, Class<T> responseType) {
        return restTemplate.exchange(uri, httpMethod, new HttpEntity<String>(createHeaders()), responseType);
    }

    private String createURI(Map<String, Object> params) {
        UriComponents uriComponents = UriComponentsBuilder
            .newInstance()
            .scheme(scheme)
            .host(host)
            .path(path)
            .uriVariables(params)
            .build();
        return uriComponents.toUriString();
    }

    private HttpHeaders createHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
