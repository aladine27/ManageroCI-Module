package com.example.backendstage.Services;

import com.example.backendstage.Entity.Build;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class GitHubActionsService {

    private final RestTemplate restTemplate;

    public GitHubActionsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void triggerBuild(Build build) {
        String url = String.format("https://api.github.com/repos/%s/actions/workflows/ci.yml/dispatches", extractRepoPath(build.getGithubUrl()));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + build.getToken());
        headers.set("Accept", "application/vnd.github.v3+json");

        String jsonBody = "{ \"ref\": \"" + build.getBranch() + "\" }";
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            build.setStatus("IN_PROGRESS");
            build.setLog("Build started...");
        } else {
            build.setStatus("FAILED");
            build.setLog("Failed to trigger build: " + response.getBody());
        }
    }

    public void updateBuildStatus(Build build) {
        // Mock logic for updating build status
        // In a real scenario, you should poll the GitHub Actions API for build status
        build.setStatus("SUCCESS");
        build.setLog("Build completed successfully.");
    }

    private String extractRepoPath(String githubUrl) {
        // Assumes GitHub URL is in the format: https://github.com/owner/repo
        return githubUrl.replace("https://github.com/", "");
    }
}
