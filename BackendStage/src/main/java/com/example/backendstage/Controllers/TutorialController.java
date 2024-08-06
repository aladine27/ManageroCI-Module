package com.example.backendstage.Controllers;

import com.example.backendstage.Entity.Tutorial;
import com.example.backendstage.Services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    private final TutorialService tutorialService;

    @Autowired
    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        List<Tutorial> tutorials = tutorialService.getAllTutorials();
        return ResponseEntity.ok(tutorials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable String id) {
        Optional<Tutorial> tutorial = tutorialService.getTutorialById(id);
        return tutorial.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

   /* @PostMapping("/add")
    public ResponseEntity<Tutorial> createTutorial(
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("imagePath") MultipartFile file) {

        try {
            String imagePath = "/uploads/" + file.getOriginalFilename(); // Assuming imagePath is stored as a path
            Tutorial tutorial = new Tutorial();
            tutorial.setTitre(titre);
            tutorial.setDescription(description);
            tutorial.setImagePath(imagePath);

            Tutorial createdTutorial = tutorialService.createTutorial(tutorial);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTutorial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
   @PostMapping("/addTuto")
   public Tutorial addtuto(@RequestBody Tutorial tutorial){

       return tutorialService.createTutorial(tutorial);
   }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable String id, @RequestBody Tutorial tutorial) {
        Tutorial updatedTutorial = tutorialService.updateTutorial(id, tutorial);
        if (updatedTutorial != null) {
            return ResponseEntity.ok(updatedTutorial);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable String id) {
        boolean deleted = tutorialService.deleteTutorial(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
