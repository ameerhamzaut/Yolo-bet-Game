package yolo.bet.project.model;

import java.util.Random;

public class RandomNumberGenerator {

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
}
