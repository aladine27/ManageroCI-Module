package com.example.backendstage.Repository;

import com.example.backendstage.Entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {}
