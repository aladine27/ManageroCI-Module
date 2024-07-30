package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Build;
import com.example.backendstage.Services.GitHubActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backendstage.Services.BuildService;


@RestController
@RequestMapping("/api/builds")
public class BuildController {

    @Autowired
    private GitHubActionsService gitHubActionsService;

    @Autowired
    private BuildService buildService;

    @PostMapping("/trigger")
    public Build triggerBuild(@RequestBody Build build) {
        build = buildService.save(build);
        gitHubActionsService.triggerBuild(build);
        return buildService.update(build);
    }

    @PostMapping("/update-status")
    public Build updateBuildStatus(@RequestBody Build build) {
        gitHubActionsService.updateBuildStatus(build);
        return buildService.update(build);
    }
}
