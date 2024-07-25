package com.example.backendstage.Services;

import com.example.backendstage.Entity.Tutorial;
import com.example.backendstage.Repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TutorialServiceTest {

    @Mock
    private TutorialRepository tutorialRepository;

    @InjectMocks
    private TutorialService tutorialService;

    @Test
    public void testGetAllTutorials() {
        Tutorial tutorial1 = new Tutorial("Test Title 1", "Test Description 1");
        Tutorial tutorial2 = new Tutorial("Test Title 2", "Test Description 2");
        List<Tutorial> tutorials = Arrays.asList(tutorial1, tutorial2);

        given(tutorialRepository.findAll()).willReturn(tutorials);

        List<Tutorial> foundTutorials = tutorialService.getAllTutorials();

        assertThat(foundTutorials).hasSize(2);
        assertThat(foundTutorials).contains(tutorial1, tutorial2);
    }

    @Test
    public void testGetTutorialById() {
        Tutorial tutorial = new Tutorial("Test Title", "Test Description");
        given(tutorialRepository.findById(anyString())).willReturn(Optional.of(tutorial));

        Optional<Tutorial> found = tutorialService.getTutorialById("1");

        assertThat(found).isPresent();
        assertThat(found.get().getTitre()).isEqualTo("Test Title");
    }

    @Test
    public void testCreateTutorial() {
        Tutorial tutorial = new Tutorial("Test Title", "Test Description");
        given(tutorialRepository.save(tutorial)).willReturn(tutorial);

        Tutorial created = tutorialService.createTutorial(tutorial);

        assertThat(created.getTitre()).isEqualTo("Test Title");
    }

    @Test
    public void testUpdateTutorial() {
        Tutorial tutorial = new Tutorial("Test Title", "Test Description");
        given(tutorialRepository.findById(anyString())).willReturn(Optional.of(tutorial));
        given(tutorialRepository.save(tutorial)).willReturn(tutorial);

        Tutorial updated = tutorialService.updateTutorial("1", tutorial);

        assertThat(updated.getTitre()).isEqualTo("Test Title");
    }

    @Test
    public void testDeleteTutorial() {
        Tutorial tutorial = new Tutorial("Test Title", "Test Description");
        given(tutorialRepository.findById(anyString())).willReturn(Optional.of(tutorial));

        boolean deleted = tutorialService.deleteTutorial("1");

        assertThat(deleted).isTrue();
        verify(tutorialRepository, times(1)).deleteById("1");
    }
}
