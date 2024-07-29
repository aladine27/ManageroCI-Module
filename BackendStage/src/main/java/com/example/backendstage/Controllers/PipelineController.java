package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Pipeline;
import com.example.backendstage.Entity.Stage;
import com.example.backendstage.Services.JenkinsService;
import com.example.backendstage.Services.PipelineService;
import com.example.backendstage.Services.StageService;
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

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private StageService stageService;

    // Obtenir tous les pipelines
    @GetMapping("/getAll")
    public List<Pipeline> getAllPipelines() {
        return pipelineService.getAllPipelines();
    }

    // Obtenir un pipeline par ID
    @GetMapping("/{id}")
    public ResponseEntity<Pipeline> getPipelineById(@PathVariable String id) {
        Optional<Pipeline> pipeline = pipelineService.getPipelineById(id);
        return pipeline.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouveau pipeline
    @PostMapping
    public ResponseEntity<Pipeline> createPipeline(
            @RequestBody Pipeline pipeline,
            @RequestParam(required = false) List<String> stageIds) {

        Pipeline createdPipeline = pipelineService.createPipeline(pipeline, stageIds);
        return ResponseEntity.ok(createdPipeline);
    }

    // Mettre à jour un pipeline existant
    @PutMapping("/{id}")
    public ResponseEntity<Pipeline> updatePipeline(
            @PathVariable String id,
            @RequestBody Pipeline pipeline) {

        Pipeline updatedPipeline = pipelineService.updatePipeline(id, pipeline);
        return updatedPipeline != null ? ResponseEntity.ok(updatedPipeline) : ResponseEntity.notFound().build();
    }

    // Supprimer un pipeline
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePipeline(@PathVariable String id) {
        pipelineService.deletePipeline(id);
        return ResponseEntity.noContent().build();
    }

    // Obtenir les stages associés à un pipeline
    @GetMapping("/{id}/stages")
    public ResponseEntity<List<Stage>> getStagesByPipelineId(@PathVariable String id) {
        List<Stage> stages = pipelineService.getStagesByPipelineId(id);
        return ResponseEntity.ok(stages);
    }

    // Ajouter des stages à un pipeline
    @PostMapping("/{id}/stages")
    public ResponseEntity<Pipeline> addStagesToPipeline(
            @PathVariable String id,
            @RequestBody List<String> stageIds) {

        Optional<Pipeline> pipelineOpt = pipelineService.getPipelineById(id);
        if (pipelineOpt.isPresent()) {
            Pipeline pipeline = pipelineOpt.get();
            pipelineService.createPipeline(pipeline, stageIds);
            return ResponseEntity.ok(pipeline);
        }
        return ResponseEntity.notFound().build();
    }
}