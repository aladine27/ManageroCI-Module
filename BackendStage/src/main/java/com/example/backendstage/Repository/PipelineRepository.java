package com.example.backendstage.Repository;

import com.example.backendstage.Entity.Pipeline;
import com.example.backendstage.Entity.Stage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PipelineRepository extends MongoRepository<Pipeline, String> {



}

