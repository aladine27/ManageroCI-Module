package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Stage;
import com.example.backendstage.Services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    // GET all stages
    @GetMapping("/getall")
    public ResponseEntity<List<Stage>> getAllStages() {
        List<Stage> stages = stageService.getAllStages();
        return new ResponseEntity<>(stages, HttpStatus.OK);
    }

    // GET stage by ID
    @GetMapping("/getstagebyid/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable String id) {
        Optional<Stage> stage = stageService.getStageById(id);
        return stage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create a new stage
    @PostMapping("/add")
    public ResponseEntity<Stage> createStage(@RequestBody Stage stage) {
        Stage createdStage = stageService.createStage(stage);
        return new ResponseEntity<>(createdStage, HttpStatus.CREATED);
    }

    // PUT update an existing stage
    @PutMapping("/modify/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable String id, @RequestBody Stage stage) {
        Stage updatedStage = stageService.updateStage(id, stage);
        if (updatedStage != null) {
            return ResponseEntity.ok(updatedStage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a stage
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable String id) {
        stageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }

    // GET stages by pipelineId
    @GetMapping("/GETstagesByPipelineId/{pipelineId}")
    public ResponseEntity<List<Stage>> getStagesByPipelineId(@PathVariable String pipelineId) {
        List<Stage> stages = stageService.getStagesByPipelineId(pipelineId);
        return new ResponseEntity<>(stages, HttpStatus.OK);
    }
}
