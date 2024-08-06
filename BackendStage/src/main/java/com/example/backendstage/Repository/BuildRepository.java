package com.example.backendstage.Repository;

import com.example.backendstage.Entity.Build;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BuildRepository extends MongoRepository<Build, String> {
    List<Build> findByProjectId(String projectId);
}

