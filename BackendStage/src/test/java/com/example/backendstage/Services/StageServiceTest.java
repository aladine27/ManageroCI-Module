package com.example.backendstage.Services;


import com.example.backendstage.Entity.Stage;
import com.example.backendstage.Repository.StageRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StageServiceTest {

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageService stageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStages() {
        Stage stage = new Stage();
        stage.setId("1");
        stage.setName("Test Stage");

        when(stageRepository.findAll()).thenReturn(Arrays.asList(stage));

        List<Stage> stages = stageService.getAllStages();
        assertEquals(1, stages.size());
        assertEquals("Test Stage", stages.get(0).getName());
    }



    @Test
    public void testCreateStage() {
        Stage stage = new Stage();
        stage.setId("1");

        when(stageRepository.save(any(Stage.class))).thenReturn(stage);

        Stage createdStage = stageService.createStage(stage);
        assertNotNull(createdStage);
        assertEquals("1", createdStage.getId());
    }

    @Test
    public void testUpdateStage() {
        Stage stage = new Stage();
        stage.setId("1");

        when(stageRepository.existsById("1")).thenReturn(true);
        when(stageRepository.save(any(Stage.class))).thenReturn(stage);

        Stage updatedStage = stageService.updateStage("1", stage);
        assertNotNull(updatedStage);
        assertEquals("1", updatedStage.getId());
    }

    @Test
    public void testDeleteStage() {
        doNothing().when(stageRepository).deleteById("1");

        stageService.deleteStage("1");
        verify(stageRepository, times(1)).deleteById("1");
    }

    @Test
    public void testGetStagesByPipelineId() {
        Stage stage = new Stage();
        stage.setPipelineId("pipeline1");

        when(stageRepository.findByPipelineId("pipeline1")).thenReturn(Arrays.asList(stage));

        List<Stage> stages = stageService.getStagesByPipelineId("pipeline1");
        assertEquals(1, stages.size());
        assertEquals("pipeline1", stages.get(0).getPipelineId());
    }
}
