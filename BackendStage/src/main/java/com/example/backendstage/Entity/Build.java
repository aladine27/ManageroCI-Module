package com.example.backendstage.Entity;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "builds")
public class Build {
    @Id
    private String id;
    private String projectId;
    private String status;
    private String log;
    private Date createdAt;
    private Date updatedAt;
    private String githubUrl;
    private String branch;
    private String token;

    public Build() {
    }

    public Build(String id, String projectId, String status, String log, Date createdAt, Date updatedAt, String githubUrl, String branch, String token) {
        this.id = id;
        this.projectId = projectId;
        this.status = status;
        this.log = log;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

