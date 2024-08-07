package com.example.backendstage.Controllers;


import com.example.backendstage.Entity.Project;
import com.example.backendstage.Services.GitHubActionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BuildController.class)
public class BuildControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GitHubActionsService gitHubActionsService;

    private Project project;

    @BeforeEach
    public void setup() {
        project = new Project();
        project.setGitUrl("https://github.com/user/repo.git");
        project.setToken("dummyToken");
    }

    @Test
    public void testTriggerBuildSuccess() throws Exception {
        String workflowFile = "workflow.yml";
        when(gitHubActionsService.triggerBuild(any(String.class), any(String.class), eq(workflowFile)))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/build/trigger")
                        .param("workflowFile", workflowFile)
                        .contentType("application/json")
                        .content("{\"gitUrl\":\"https://github.com/user/repo.git\",\"token\":\"dummyToken\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }

    @Test
    public void testTriggerBuildFailure() throws Exception {
        String workflowFile = "workflow.yml";
        when(gitHubActionsService.triggerBuild(any(String.class), any(String.class), eq(workflowFile)))
                .thenReturn(new ResponseEntity<>("Failed to trigger build", HttpStatus.INTERNAL_SERVER_ERROR));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/build/trigger")
                        .param("workflowFile", workflowFile)
                        .contentType("application/json")
                        .content("{\"gitUrl\":\"https://github.com/user/repo.git\",\"token\":\"dummyToken\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to trigger build"));
    }
}