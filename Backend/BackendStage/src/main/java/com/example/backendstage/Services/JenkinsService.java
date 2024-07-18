package com.example.backendstage.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JenkinsService {

    @Autowired
    private RestTemplate restTemplate;

    public String createJenkinsJob(String jobName, String gitUrl) {
        // URL de l'API Jenkins pour créer un job
        String apiUrl = "http://jenkins.example.com/createItem?name=" + jobName;

        // Configuration XML du job Jenkins
        String xmlConfig = "<project>"
                + "<actions/>"
                + "<description>Job créé par l'API</description>"
                + "<scm class='hudson.plugins.git.GitSCM' plugin='git@4.0.0'>"
                + "<configVersion>2</configVersion>"
                + "<userRemoteConfigs>"
                + "<hudson.plugins.git.UserRemoteConfig>"
                + "<url>" + gitUrl + "</url>"
                + "</hudson.plugins.git.UserRemoteConfig>"
                + "</userRemoteConfigs>"
                + "<branches>"
                + "<hudson.plugins.git.BranchSpec>"
                + "<name>*/main</name>"
                + "</hudson.plugins.git.BranchSpec>"
                + "</branches>"
                + "<doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>"
                + "<submoduleCfg class='list'/>"
                + "<extensions/>"
                + "</scm>"
                + "<builders/>"
                + "<publishers/>"
                + "<buildWrappers/>"
                + "</project>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(xmlConfig, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return "http://jenkins.example.com/job/" + jobName;
        } else {
            throw new RuntimeException("Failed to create Jenkins job");
        }
    }

    public String triggerJenkinsJob(String jobUrl) {
        String buildUrl = jobUrl + "/build";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.postForEntity(buildUrl, request, String.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return buildUrl + "/lastBuild";
        } else {
            throw new RuntimeException("Failed to trigger Jenkins job");
        }
    }

    public String getJenkinsJobStatus(String jobUrl) {
        String apiUrl = jobUrl + "/lastBuild/api/json";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get Jenkins job status");
        }
    }
}
