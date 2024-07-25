package com.example.backendstage.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pipelines")
public class Pipeline {
    @Id
    private String id;

    public Pipeline(String id, String name, List<Stage> stages, String jenkinsJobUrl, String projectId) {
        this.id = id;
        this.name = name;
        this.stages = stages;
        this.jenkinsJobUrl = jenkinsJobUrl;
        this.projectId = projectId;
    }
    public Pipeline() {
        // Constructeur par défaut
    }


    private String name;
    private List<Stage> stages; // Liste d'objets Stage
    private String jenkinsJobUrl;
    private String projectId; // Référence au projet

    // Getters et setters
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

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    @Override
    public String toString() {
        return "Pipeline{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stages=" + stages +
                ", jenkinsJobUrl='" + jenkinsJobUrl + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
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

