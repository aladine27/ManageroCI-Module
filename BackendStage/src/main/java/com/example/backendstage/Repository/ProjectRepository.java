package com.example.backendstage.Repository;

import com.example.backendstage.Entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String> {
    Optional<Project> findByGitUrl(String gitUrl);
}
