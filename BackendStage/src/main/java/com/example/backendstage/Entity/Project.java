package com.example.backendstage.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    private String gitUrl;
    private String token;  // Ajout du champ token
    private String gitUsername; // New field for GitHub username
    private String gitRepo;
    private String sonarToken; // Add this field for SonarQube token

    public Project() {
    }

    public Project(String id, String name, String description, String gitUrl, String token, String gitUsername, String gitRepo, String sonarToken) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gitUrl = gitUrl;
        this.token = token;
        this.gitUsername = gitUsername;
        this.gitRepo = gitRepo;
        this.sonarToken = sonarToken;
    }

    public String getSonarToken() {
        return sonarToken;
    }

    public void setSonarToken(String sonarToken) {
        this.sonarToken = sonarToken;
    }

    public String getGitUsername() {
        return gitUsername;
    }

    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }

    public String getGitRepo() {
        return gitRepo;
    }

    public void setGitRepo(String gitRepo) {
        this.gitRepo = gitRepo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
        extractGitUsernameAndRepo();
    }
    private void extractGitUsernameAndRepo() {
        // URL : https://github.com/username/repo
        if (gitUrl != null && gitUrl.startsWith("https://github.com/")) {
            String[] parts = gitUrl.replace("https://github.com/", "").split("/");
            if (parts.length == 2) {
                this.gitUsername = parts[0];
                this.gitRepo = parts[1];
            }
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
