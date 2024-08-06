package com.example.backendstage.Controllers;


import com.example.backendstage.Entity.Tutorial;

import com.example.backendstage.Services.TutorialService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TutorialControllerTest {

    @Mock
    private TutorialService tutorialService;

    @InjectMocks
    private TutorialController tutorialController;

    private MockMvc mockMvc;

    @Test
    public void testGetAllTutorials() throws Exception {
        Tutorial tutorial1 = new Tutorial("Test Title 1", "Test Description 1");
        Tutorial tutorial2 = new Tutorial("Test Title 2", "Test Description 2");
        given(tutorialService.getAllTutorials()).willReturn(Arrays.asList(tutorial1, tutorial2));

        mockMvc = MockMvcBuilders.standaloneSetup(tutorialController).build();

        mockMvc.perform(get("/api/tutorials/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titre").value("Test Title 1"))
                .andExpect(jsonPath("$[1].titre").value("Test Title 2"));
    }

    @Test
    public void testGetTutorialById() throws Exception {
        Tutorial tutorial = new Tutorial("Test Title", "Test Description");
        given(tutorialService.getTutorialById(anyString())).willReturn(Optional.of(tutorial));

        mockMvc = MockMvcBuilders.standaloneSetup(tutorialController).build();

        mockMvc.perform(get("/api/tutorials/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Test Title"));
    }

    @Test
    public void testCreateTutorial() throws Exception {
        Tutorial tutorial = new Tutorial("Test Title", "Test Description");
        given(tutorialService.createTutorial(tutorial)).willReturn(tutorial);

        mockMvc = MockMvcBuilders.standaloneSetup(tutorialController).build();

        mockMvc.perform(post("/api/tutorials/addTuto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titre\": \"Test Title\", \"description\": \"Test Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Test Title"));
    }
}
