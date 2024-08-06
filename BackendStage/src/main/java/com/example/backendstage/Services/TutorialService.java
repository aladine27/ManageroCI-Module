package com.example.backendstage.Services;
import com.example.backendstage.Entity.Tutorial;
import com.example.backendstage.Repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    @Autowired
    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    public Optional<Tutorial> getTutorialById(String id) {
        return tutorialRepository.findById(id);
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Tutorial updateTutorial(String id, Tutorial tutorial) {
        if (tutorialRepository.existsById(id)) {
            tutorial.setId(id);
            return tutorialRepository.save(tutorial);
        }
        return null; // Gérer le cas où le tutoriel n'est pas trouvé
    }

    public boolean deleteTutorial(String id) {
        if (tutorialRepository.existsById(id)) {
            tutorialRepository.deleteById(id);
            return true;
        }
        return false; // Gérer le cas où le tutoriel n'est pas trouvé
    }


}

