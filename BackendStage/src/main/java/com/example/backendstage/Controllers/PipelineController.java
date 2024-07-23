package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Pipeline;
import com.example.backendstage.Services.PipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pipelines")
public class PipelineController {

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
        Pipeline createdPipeline = pipelineService.createPipeline(pipeline);
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
