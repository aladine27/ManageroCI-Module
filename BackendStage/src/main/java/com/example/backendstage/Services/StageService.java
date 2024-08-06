package com.example.backendstage.Services;

import com.example.backendstage.Entity.Stage;

import com.example.backendstage.Repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    // Obtenir tous les stages
    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    // Obtenir un stage par ID
    public Optional<Stage> getStageById(String id) {
        return stageRepository.findById(id);
    }

    // Créer un nouveau stage
    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    // Mettre à jour un stage existant
    public Stage updateStage(String id, Stage stage) {
        if (stageRepository.existsById(id)) {
            stage.setId(id);
            return stageRepository.save(stage);
        } else {
            return null;
        }
    }

    // Supprimer un stage
    public void deleteStage(String id) {
        stageRepository.deleteById(id);
    }

    // Obtenir les stages par pipelineId
    public List<Stage> getStagesByPipelineId(String pipelineId) {
        return stageRepository.findByPipelineId(pipelineId);
    }
}
