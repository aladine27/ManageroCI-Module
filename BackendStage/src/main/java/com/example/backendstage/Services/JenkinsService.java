package com.example.backendstage.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JenkinsService {
    private static final Logger logger = LoggerFactory.getLogger(JenkinsService.class);
    @Value("${jenkins.url}")
    private String jenkinsUrl;
    @Value("${jenkins.api.token}")
    private String jenkinsApiToken;

    @Value("${jenkins.job.name}")
    private String jenkinsJobName;

    @Autowired
    private RestTemplate restTemplate;

    public String createJenkinsJob(String jobName, String gitUrl) {
        // URL de l'API Jenkins pour créer un job
        String apiUrl = "http://localhost:8081/createItem?name=" + jobName;

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
            return "http://localhost:8081/job/" + jobName;
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



    // Méthode pour déclencher un build Jenkins
    public String triggerBuild(String jobName) {
        String buildUrl = jenkinsUrl + "/job/" + jobName + "/build";
        logger.info("Triggering Jenkins build at URL: {}", buildUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jenkinsApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(buildUrl, HttpMethod.POST, entity, String.class);
        logger.info("Response status from Jenkins build trigger: {}", response.getStatusCode());

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return buildUrl + "/lastBuild";
        } else {
            logger.error("Failed to trigger Jenkins build. Response body: {}", response.getBody());
            throw new RuntimeException("Failed to trigger Jenkins build");
        }
    }

    public String getJenkinsJobStatus(String jobUrl) {
        String apiUrl = jobUrl + "/lastBuild/api/json";
        logger.info("Getting Jenkins job status from URL: {}", apiUrl);

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        logger.info("Response status from Jenkins job status request: {}", response.getStatusCode());

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            logger.error("Failed to get Jenkins job status. Response body: {}", response.getBody());
            throw new RuntimeException("Failed to get Jenkins job status");
        }
    }
}
