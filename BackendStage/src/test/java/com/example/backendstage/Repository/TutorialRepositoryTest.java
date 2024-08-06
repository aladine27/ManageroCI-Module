package com.example.backendstage.Repository;

import com.example.backendstage.Entity.Tutorial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class TutorialRepositoryTest {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Test
    public void testSaveAndFindById() {
        Tutorial tutorial = new Tutorial("Test Title", "Test Description");
        tutorial = tutorialRepository.save(tutorial);

        Optional<Tutorial> found = tutorialRepository.findById(tutorial.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitre()).isEqualTo("Test Title");
        assertThat(found.get().getDescription()).isEqualTo("Test Description");
    }

    @Test
    public void testDelete() {
        Tutorial tutorial = new Tutorial("Test Title", "Test Description");
        tutorial = tutorialRepository.save(tutorial);

        tutorialRepository.deleteById(tutorial.getId());

        Optional<Tutorial> found = tutorialRepository.findById(tutorial.getId());
        assertThat(found).isNotPresent();
    }
}
