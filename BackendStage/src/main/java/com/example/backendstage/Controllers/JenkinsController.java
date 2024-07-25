package com.example.backendstage.Controllers;

import com.example.backendstage.Services.JenkinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jenkins")
public class JenkinsController {

    @Autowired
    private JenkinsService jenkinsService;

    // Endpoint pour créer un job Jenkins
    @PostMapping("/create-job")
    public ResponseEntity<String> createJenkinsJob(@RequestParam String jobName, @RequestParam String gitUrl) {
        try {
            String jobUrl = jenkinsService.createJenkinsJob(jobName, gitUrl);
            return ResponseEntity.ok(jobUrl);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    // Endpoint pour déclencher un job Jenkins
    @PostMapping("/trigger-job")
    public ResponseEntity<String> triggerJenkinsJob(@RequestParam String jobUrl) {
        try {
            String buildUrl = jenkinsService.triggerJenkinsJob(jobUrl);
            return ResponseEntity.ok(buildUrl);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    // Endpoint pour obtenir le statut d'un job Jenkins
    @GetMapping("/job-status")
    public ResponseEntity<String> getJenkinsJobStatus(@RequestParam String jobUrl) {
        try {
            String status = jenkinsService.getJenkinsJobStatus(jobUrl);
            return ResponseEntity.ok(status);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
