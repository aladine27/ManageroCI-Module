package com.example.backendstage.Services;


import com.example.backendstage.config.GitHubConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class GitHubActionsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GitHubConfig gitHubConfig;

    @InjectMocks
    private GitHubActionsService gitHubActionsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTriggerBuildSuccess() {
        String gitUrl = "https://github.com/user/repo.git";
        String token = "dummyToken";
        String workflowFile = "workflow.yml";

        String workflowUrl = "https://api.github.com/repos/user/repo/actions/workflows/workflow.yml/dispatches";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + token);
        headers.set("Accept", "application/vnd.github.v3+json");

        String body = "{\"ref\": \"main\"}";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);

        when(gitHubConfig.getUrl()).thenReturn("https://api.github.com");
        when(restTemplate.exchange(eq(workflowUrl), eq(HttpMethod.POST), eq(entity), eq(String.class)))
                .thenReturn(responseEntity);

        ResponseEntity<String> response = gitHubActionsService.triggerBuild(gitUrl, token, workflowFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }

    @Test
    public void testTriggerBuildFailure() {
        String gitUrl = "https://github.com/user/repo.git";
        String token = "dummyToken";
        String workflowFile = "workflow.yml";

        String workflowUrl = "https://api.github.com/repos/user/repo/actions/workflows/workflow.yml/dispatches";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + token);
        headers.set("Accept", "application/vnd.github.v3+json");

        String body = "{\"ref\": \"main\"}";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        when(gitHubConfig.getUrl()).thenReturn("https://api.github.com");
        when(restTemplate.exchange(eq(workflowUrl), eq(HttpMethod.POST), eq(entity), eq(String.class)))
                .thenThrow(new RestClientException("Failed to trigger build"));

        ResponseEntity<String> response = gitHubActionsService.triggerBuild(gitUrl, token, workflowFile);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to trigger build: Failed to trigger build", response.getBody());
    }
}
