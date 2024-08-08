package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Project;
import com.example.backendstage.Services.GitHubActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/build")
public class BuildController {

    private final GitHubActionsService gitHubActionsService;

    @Autowired
    public BuildController(GitHubActionsService gitHubActionsService) {
        this.gitHubActionsService = gitHubActionsService;
    }

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerBuild(@RequestBody Project project, @RequestParam String workflowFile) {
        return gitHubActionsService.triggerBuild(project.getGitUrl(), project.getToken(), workflowFile);
    }

}