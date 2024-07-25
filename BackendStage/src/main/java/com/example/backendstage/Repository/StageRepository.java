package com.example.backendstage.Repository;

import com.example.backendstage.Entity.Stage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StageRepository extends MongoRepository<Stage, String> {
    // Rechercher les stages par pipelineId
    List<Stage> findByPipelineId(String pipelineId);
}
