package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Pipeline;
import com.example.backendstage.Services.JenkinsService;
import com.example.backendstage.Services.PipelineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pipelines")
public class PipelineController {
    private static final Logger logger = LoggerFactory.getLogger(PipelineController.class);

    @Autowired
    private JenkinsService jenkinsService;

    @Autowired
    private PipelineService pipelineService;

    @GetMapping("/getall")
    public ResponseEntity<List<Pipeline>> getAllPipelines() {
        List<Pipeline> pipelines = pipelineService.getAllPipelines();
        return new ResponseEntity<>(pipelines, HttpStatus.OK);
    }

    @GetMapping("/getpiplineby/{id}")
    public ResponseEntity<Pipeline> getPipelineById(@PathVariable String id) {
        Optional<Pipeline> pipeline = pipelineService.getPipelineById(id);
        return pipeline.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Pipeline> createPipeline(@RequestBody Pipeline pipeline) {
        logger.info("Creating pipeline with data: {}", pipeline);
        Pipeline createdPipeline = pipelineService.createPipeline(pipeline);
        logger.info("Pipeline created: {}", createdPipeline);

        try {
            // Déclencher le build Jenkins après la création du pipeline
            jenkinsService.triggerBuild(createdPipeline.getName()); // Utiliser le nom du pipeline pour le job Jenkins
            logger.info("Build triggered for job: {}", createdPipeline.getName());
        } catch (Exception e) {
            logger.error("Error triggering Jenkins build", e);
        }

        return new ResponseEntity<>(createdPipeline, HttpStatus.CREATED);
    }
    @PutMapping("/modify/{id}")
    public ResponseEntity<Pipeline> updatePipeline(@PathVariable String id, @RequestBody Pipeline pipeline) {
        Pipeline updatedPipeline = pipelineService.updatePipeline(id, pipeline);
        if (updatedPipeline != null) {
            return ResponseEntity.ok(updatedPipeline);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePipeline(@PathVariable String id) {
        pipelineService.deletePipeline(id);
        return ResponseEntity.noContent().build();
    }
}
