package yolo.bet.project;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import yolo.bet.project.controller.ProjectController;
import yolo.bet.project.model.Project;
import yolo.bet.project.model.RandomNumberGenerator;

@WebMvcTest(ProjectController.class)
public class ProjectControllerIntTests {

    @Autowired
    private MockMvc mockMvc;

    // @MockBean
    // private Random random;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private RandomNumberGenerator randomNumberGenerator;

    @Test
    public void test_WinScenario() throws Exception {
        int randomNumber = 30;
        when(randomNumberGenerator.generateRandomNumber()).thenReturn(randomNumber);

        Project bet = new Project();
        bet.setBet(40.5);
        bet.setWholenumber(50);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/bet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bet)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("80.19"));

    }

    @Test
    public void test_LossScenario() throws Exception {
        int randomNumber = 60;
        when(randomNumberGenerator.generateRandomNumber()).thenReturn(randomNumber);

        Project bet = new Project();
        bet.setBet(40.5);
        bet.setWholenumber(50);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/bet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bet)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("0.0"));

    }
}
