package com.example.backendstage.Services;

import com.example.backendstage.Entity.Project;

import com.example.backendstage.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // GET all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // GET project by ID
    public Optional<Project> getProjectById(String id) {
        return projectRepository.findById(id);
    }

    // POST create a new project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // PUT update an existing project
    public Project updateProject(String id, Project project) {
        if (projectRepository.existsById(id)) {
            project.setId(id);
            return projectRepository.save(project);
        } else {
            return null;
        }
    }

    // DELETE a project
    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
}
