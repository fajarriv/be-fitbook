package rpl.fitbook.controller;

import rpl.fitbook.controller.trainer.TrainerController;
import rpl.fitbook.service.trainer.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TrainerRatingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(trainerController).build();
    }

    @Test
    public void rateTrainer_ShouldReturnOk_WhenRatingIsValid() throws Exception {
        Long trainerId = 1L;
        Float rating = 4.5f;

        doNothing().when(trainerService).rateTrainer(trainerId, rating);

        mockMvc.perform(post("/api/trainers/{trainerId}/rate", trainerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rating.toString()))
                .andExpect(status().isOk());
    }
}
