package probability_sampling_randomization;

import java.util.Random;

public class BoundedRandomNumber {
    /**
     * Write a function that returns a random number within the range of [a, b] with equal probabilities.
     * @param a lower bound
     * @param b upper bound
     * @return a randomized number between [a, b]
     */
    public int random(int a, int b) {
        /*
                [a, b] --> [0, b - a] --> [0, b - a + 1) + a
         */
        Random random = new Random();
        return  random.nextInt(b - a + 1) + a;
    }
}
