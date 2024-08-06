package com.example.backendstage.Services;

import com.example.backendstage.config.GitHubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


@Service
public class GitHubActionsService {

    private final RestTemplate restTemplate;
    private final GitHubConfig gitHubConfig;

    @Autowired
    public GitHubActionsService(RestTemplate restTemplate, GitHubConfig gitHubConfig) {
        this.restTemplate = restTemplate;
        this.gitHubConfig = gitHubConfig;
    }

    public ResponseEntity<String> triggerBuild(String gitUrl, String token, String workflowFile) {
        try {
            String repoPath = extractRepoPath(gitUrl);
            String workflowUrl = gitHubConfig.getUrl() + "/repos/" + repoPath + "/actions/workflows/" + workflowFile + "/dispatches";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "token " + token);
            headers.set("Accept", "application/vnd.github.v3+json");

            String body = "{\"ref\": \"main\"}";

            System.out.println("Triggering build with URL: " + workflowUrl);
            System.out.println("Headers: " + headers.toString());

            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            return restTemplate.exchange(workflowUrl, HttpMethod.POST, entity, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to trigger build: " + e.getMessage());
        }
    }

    private String extractRepoPath(String gitUrl) {
        return gitUrl.replace("https://github.com/", "").replace(".git", "");
    }
}
