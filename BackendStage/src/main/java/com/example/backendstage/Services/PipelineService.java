package com.example.backendstage.Services;

import com.example.backendstage.Entity.Pipeline;
import com.example.backendstage.Entity.Stage;
import com.example.backendstage.Repository.PipelineRepository;
import com.example.backendstage.Repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PipelineService {
    @Autowired
    private PipelineRepository pipelineRepository;

    @Autowired
    private StageRepository stageRepository;

    public List<Pipeline> getAllPipelines() {
        return pipelineRepository.findAll();
    }

    public Optional<Pipeline> getPipelineById(String id) {
        return pipelineRepository.findById(id);
    }


    public Pipeline createPipeline(Pipeline pipeline) {
        // Sauvegarde du pipeline
        Pipeline savedPipeline = pipelineRepository.save(pipeline);

        // Sauvegarde des stages
        List<Stage> stages = savedPipeline.getStages();
        if (stages != null && !stages.isEmpty()) {
            for (Stage stage : stages) {
                stage.setPipelineId(savedPipeline.getId()); // Assurez-vous que l'ID du pipeline est défini
                stageRepository.save(stage);
            }
        }

        return savedPipeline;
    }


    public Pipeline updatePipeline(String id, Pipeline pipeline) {
        pipeline.setId(id);
        return pipelineRepository.save(pipeline);
    }

    public void deletePipeline(String id) {
        pipelineRepository.deleteById(id);
    }

    public List<Stage> getStagesByPipelineId(String pipelineId) {
        return stageRepository.findByPipelineId(pipelineId);
    }

    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Stage updateStage(String id, Stage stage) {
        stage.setId(id);
        return stageRepository.save(stage);
    }

    public void deleteStage(String id) {
        stageRepository.deleteById(id);
    }
}