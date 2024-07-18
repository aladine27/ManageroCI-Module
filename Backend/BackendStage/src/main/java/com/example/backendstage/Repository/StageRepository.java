package com.example.backendstage.Repository;

import com.example.backendstage.Entity.Stage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StageRepository extends MongoRepository<Stage, String> {
    List<Stage> findByPipelineId(String pipelineId);
}
