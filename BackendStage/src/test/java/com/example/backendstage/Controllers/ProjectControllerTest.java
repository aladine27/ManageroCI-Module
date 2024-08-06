package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Project;

import com.example.backendstage.Services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProjects() {
        Project project = new Project();
        project.setId("1");

        when(projectService.getAllProjects()).thenReturn(Arrays.asList(project));

        ResponseEntity<List<Project>> response = projectController.getAllProjects();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("1", response.getBody().get(0).getId());
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project();
        project.setId("1");

        when(projectService.getProjectById("1")).thenReturn(Optional.of(project));

        ResponseEntity<Project> response = projectController.getProjectById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testCreateProject() {
        Project project = new Project();
        project.setId("1");

        when(projectService.createProject(any(Project.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.createProject(project);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testUpdateProject() {
        Project project = new Project();
        project.setId("1");

        when(projectService.updateProject(eq("1"), any(Project.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.updateProject("1", project);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testDeleteProject() {
        doNothing().when(projectService).deleteProject("1");

        ResponseEntity<Void> response = projectController.deleteProject("1");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(projectService, times(1)).deleteProject("1");
    }
}
