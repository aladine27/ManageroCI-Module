package com.example.backendstage.Services;



import com.example.backendstage.Entity.Pipeline;
import com.example.backendstage.Entity.Stage;
import com.example.backendstage.Repository.PipelineRepository;
import com.example.backendstage.Repository.StageRepository;

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

public class PipelineServiceTest {

    @Mock
    private PipelineRepository pipelineRepository;

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private PipelineService pipelineService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPipelines() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");

        when(pipelineRepository.findAll()).thenReturn(Arrays.asList(pipeline));

        List<Pipeline> pipelines = pipelineService.getAllPipelines();
        assertEquals(1, pipelines.size());
        assertEquals("1", pipelines.get(0).getId());
    }

    @Test
    public void testGetPipelineById() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");

        when(pipelineRepository.findById("1")).thenReturn(Optional.of(pipeline));

        Optional<Pipeline> result = pipelineService.getPipelineById("1");
        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
    }

    @Test
    public void testCreatePipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");
        Stage stage = new Stage();
        stage.setId("stage1");

        when(pipelineRepository.save(any(Pipeline.class))).thenReturn(pipeline);
        when(stageRepository.findById("stage1")).thenReturn(Optional.of(stage));
        when(stageRepository.save(any(Stage.class))).thenReturn(stage);

        Pipeline createdPipeline = pipelineService.createPipeline(pipeline, Arrays.asList("stage1"));
        assertNotNull(createdPipeline);
        assertEquals("1", createdPipeline.getId());
        verify(stageRepository, times(1)).save(stage);
    }

    @Test
    public void testUpdatePipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.setId("1");

        when(pipelineRepository.save(any(Pipeline.class))).thenReturn(pipeline);

        Pipeline updatedPipeline = pipelineService.updatePipeline("1", pipeline);
        assertNotNull(updatedPipeline);
        assertEquals("1", updatedPipeline.getId());
    }

    @Test
    public void testDeletePipeline() {
        doNothing().when(pipelineRepository).deleteById("1");

        pipelineService.deletePipeline("1");
        verify(pipelineRepository, times(1)).deleteById("1");
    }

    @Test
    public void testGetStagesByPipelineId() {
        Stage stage = new Stage();
        stage.setPipelineId("pipeline1");

        when(stageRepository.findByPipelineId("pipeline1")).thenReturn(Arrays.asList(stage));

        List<Stage> stages = pipelineService.getStagesByPipelineId("pipeline1");
        assertEquals(1, stages.size());
        assertEquals("pipeline1", stages.get(0).getPipelineId());
    }
}
