package com.example.backendstage.Services;

import com.example.backendstage.Entity.Build;
import com.example.backendstage.Repository.BuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BuildService {
    @Autowired
    private BuildRepository buildRepository;

    public Build save(Build build) {
        build.setCreatedAt(new Date());
        build.setUpdatedAt(new Date());
        return buildRepository.save(build);
    }

    public List<Build> findByProjectId(String projectId) {
        return buildRepository.findByProjectId(projectId);
    }

    public Optional<Build> findById(String id) {
        return buildRepository.findById(id);
    }

    public Build update(Build build) {
        build.setUpdatedAt(new Date());
        return buildRepository.save(build);
    }
}

