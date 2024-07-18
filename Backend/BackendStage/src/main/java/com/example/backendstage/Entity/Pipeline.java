package com.example.backendstage.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pipelines")
public class Pipeline {
    @Id
    private String id;
    private String name;
    private List<Stage> stages;
    private String jenkinsJobUrl;

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

    public String getJenkinsJobUrl() {
        return jenkinsJobUrl;
    }

    public void setJenkinsJobUrl(String jenkinsJobUrl) {
        this.jenkinsJobUrl = jenkinsJobUrl;
    }
// getters and setters
}
