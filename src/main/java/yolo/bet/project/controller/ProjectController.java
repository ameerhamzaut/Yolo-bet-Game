package yolo.bet.project.controller;

import yolo.bet.project.model.RandomNumberGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import yolo.bet.project.model.Project;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ProjectController {

    @Autowired
    private RandomNumberGenerator randomNumberGenerator;

    @PostMapping("/bet")
    @MessageMapping("/bet")
    @SendTo("/topic/bet")
    public ResponseEntity<Double> playBet(@RequestBody @Validated Project bet) {

        // Random number
        int randomNumber = randomNumberGenerator.generateRandomNumber();

        log.info("bet.getBet(): {}", bet.getBet());
        log.info("bet.getWholenumber(): {}", bet.getWholenumber());
        log.info("randomNumber: {}", randomNumber);

        // if wholenumber is greater than the random number
        if (bet.getWholenumber() > randomNumber) {

            Double chance = bet.getBet() * (99.0 / (100 - bet.getWholenumber()));
            log.info("chance: {}", chance);

            // String responseString = "You have won: " + chance;
            // return ResponseEntity.ok(responseString);

            return ResponseEntity.ok(chance);

        } else {
            // Player lost
            Double chance = 0.0;
            System.out.println("chance: " + chance);

            // String responseString = "You Lost: " + chance;
            // return ResponseEntity.ok(responseString);

            return ResponseEntity.ok(chance);
        }
    }

}
