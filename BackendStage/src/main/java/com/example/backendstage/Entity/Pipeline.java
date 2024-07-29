package com.example.backendstage.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pipelines")
public class Pipeline {
    @Id
    private String id;
    private String name;
    private List<String> stageIds; // Liste des IDs des stages associés
    private String jenkinsJobUrl;
    private String projectId; // Référence au projet

    public Pipeline(String id, String name, List<String> stageIds, String jenkinsJobUrl, String projectId) {
        this.id = id;
        this.name = name;
        this.stageIds = stageIds;
        this.jenkinsJobUrl = jenkinsJobUrl;
        this.projectId = projectId;
    }

    public Pipeline() {
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

    public List<String> getStageIds() {
        return stageIds;
    }

    public void setStageIds(List<String> stageIds) {
        this.stageIds = stageIds;
    }

    public String getJenkinsJobUrl() {
        return jenkinsJobUrl;
    }

    public void setJenkinsJobUrl(String jenkinsJobUrl) {
        this.jenkinsJobUrl = jenkinsJobUrl;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}