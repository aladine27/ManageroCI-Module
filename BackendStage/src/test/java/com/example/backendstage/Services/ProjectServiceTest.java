package com.example.backendstage.Services;

import com.example.backendstage.Entity.Project;
import com.example.backendstage.Repository.ProjectRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProjects() {
        Project project = new Project();
        project.setId("1");

        when(projectRepository.findAll()).thenReturn(Arrays.asList(project));

        List<Project> projects = projectService.getAllProjects();
        assertEquals(1, projects.size());
        assertEquals("1", projects.get(0).getId());
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project();
        project.setId("1");

        when(projectRepository.findById("1")).thenReturn(Optional.of(project));

        Optional<Project> result = projectService.getProjectById("1");
        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
    }

    @Test
    public void testCreateProject() {
        Project project = new Project();
        project.setId("1");

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);
        assertNotNull(createdProject);
        assertEquals("1", createdProject.getId());
    }

    @Test
    public void testUpdateProject() {
        Project project = new Project();
        project.setId("1");

        when(projectRepository.existsById("1")).thenReturn(true);
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = projectService.updateProject("1", project);
        assertNotNull(updatedProject);
        assertEquals("1", updatedProject.getId());
    }

    @Test
    public void testDeleteProject() {
        doNothing().when(projectRepository).deleteById("1");

        projectService.deleteProject("1");
        verify(projectRepository, times(1)).deleteById("1");
    }
}
