package yolo.bet.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import yolo.bet.project.controller.ProjectController;
import yolo.bet.project.model.Project;
import yolo.bet.project.model.RandomNumberGenerator;

public class ProjectControllerUnitTests {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private RandomNumberGenerator randomNumberGenerator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_WinningBet() {

        Project bet = new Project();
        bet.setWholenumber(50);
        bet.setBet(40.5);

        int randomNumber = 30;
        when(randomNumberGenerator.generateRandomNumber()).thenReturn(randomNumber);

        ResponseEntity<Double> response = projectController.playBet(bet);
        assertNotNull(response);
        assertEquals(80.19, response.getBody());
    }

    @Test
    void test_LossingBet() {

        Project bet = new Project();
        bet.setWholenumber(50);
        bet.setBet(40.5);

        int randomNumber = 60;
        when(randomNumberGenerator.generateRandomNumber()).thenReturn(randomNumber);

        ResponseEntity<Double> response = projectController.playBet(bet);
        assertNotNull(response);
        assertEquals(0.0, response.getBody());
    }

}
