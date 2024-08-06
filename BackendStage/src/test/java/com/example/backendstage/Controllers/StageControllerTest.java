package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Stage;

import com.example.backendstage.Services.StageService;
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

public class StageControllerTest {

    @Mock
    private StageService stageService;

    @InjectMocks
    private StageController stageController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStages() {
        Stage stage = new Stage();
        stage.setId("1");

        when(stageService.getAllStages()).thenReturn(Arrays.asList(stage));

        ResponseEntity<List<Stage>> response = stageController.getAllStages();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("1", response.getBody().get(0).getId());
    }

    @Test
    public void testGetStageById() {
        Stage stage = new Stage();
        stage.setId("1");

        when(stageService.getStageById("1")).thenReturn(Optional.of(stage));

        ResponseEntity<Stage> response = stageController.getStageById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testCreateStage() {
        Stage stage = new Stage();
        stage.setId("1");

        when(stageService.createStage(any(Stage.class))).thenReturn(stage);

        ResponseEntity<Stage> response = stageController.createStage(stage);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testUpdateStage() {
        Stage stage = new Stage();
        stage.setId("1");

        when(stageService.updateStage(eq("1"), any(Stage.class))).thenReturn(stage);

        ResponseEntity<Stage> response = stageController.updateStage("1", stage);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testDeleteStage() {
        doNothing().when(stageService).deleteStage("1");

        ResponseEntity<Void> response = stageController.deleteStage("1");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(stageService, times(1)).deleteStage("1");
    }

    @Test
    public void testGetStagesByPipelineId() {
        Stage stage = new Stage();
        stage.setPipelineId("pipeline1");

        when(stageService.getStagesByPipelineId("pipeline1")).thenReturn(Arrays.asList(stage));

        ResponseEntity<List<Stage>> response = stageController.getStagesByPipelineId("pipeline1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("pipeline1", response.getBody().get(0).getPipelineId());
    }
}
