package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Pipeline;
import com.example.backendstage.Entity.Stage;

import com.example.backendstage.Services.PipelineService;
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

public class PipelineControllerTest {

    @Mock
    private PipelineService pipelineService;

    @Mock
    private StageService stageService;

    @InjectMocks
    private PipelineController pipelineController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPipelines() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");

        when(pipelineService.getAllPipelines()).thenReturn(Arrays.asList(pipeline));

        List<Pipeline> pipelines = pipelineController.getAllPipelines();
        assertEquals(1, pipelines.size());
        assertEquals("1", pipelines.get(0).getId());
    }

    @Test
    public void testGetPipelineById() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");

        when(pipelineService.getPipelineById("1")).thenReturn(Optional.of(pipeline));

        ResponseEntity<Pipeline> response = pipelineController.getPipelineById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testCreatePipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");

        when(pipelineService.createPipeline(any(Pipeline.class), any())).thenReturn(pipeline);

        ResponseEntity<Pipeline> response = pipelineController.createPipeline(pipeline, Arrays.asList("stage1"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testUpdatePipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");

        when(pipelineService.updatePipeline(eq("1"), any(Pipeline.class))).thenReturn(pipeline);

        ResponseEntity<Pipeline> response = pipelineController.updatePipeline("1", pipeline);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }

    @Test
    public void testDeletePipeline() {
        doNothing().when(pipelineService).deletePipeline("1");

        ResponseEntity<Void> response = pipelineController.deletePipeline("1");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pipelineService, times(1)).deletePipeline("1");
    }

    @Test
    public void testGetStagesByPipelineId() {
        Stage stage = new Stage();
        stage.setPipelineId("pipeline1");

        when(pipelineService.getStagesByPipelineId("pipeline1")).thenReturn(Arrays.asList(stage));

        ResponseEntity<List<Stage>> response = pipelineController.getStagesByPipelineId("pipeline1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("pipeline1", response.getBody().get(0).getPipelineId());
    }

    @Test
    public void testAddStagesToPipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");

        when(pipelineService.getPipelineById("1")).thenReturn(Optional.of(pipeline));
        when(pipelineService.createPipeline(any(Pipeline.class), any())).thenReturn(pipeline);

        ResponseEntity<Pipeline> response = pipelineController.addStagesToPipeline("1", Arrays.asList("stage1"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
    }
}

