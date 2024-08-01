package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Build;
import com.example.backendstage.Entity.Project;
import com.example.backendstage.Repository.ProjectRepository;
import com.example.backendstage.Services.GitHubActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backendstage.Services.BuildService;

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