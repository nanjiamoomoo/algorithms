package probability_sampling_randomization;

import java.util.Random;

public class Random7UsingRandom5 {
    /**
     * return random number from 0 ~ 6
     * @return
     */
    public int random7() {
        while (true) {
            int r = random25();
            /*
                Reduce the loop count largely and save time complexity by using a larger number. This is why we use 21 instead 7.
             */
            if (r < 21) {
                return r % 7;
            }
        }
    }

    /**
     * return random number from 0 ~ 5 ^ 2 - 1
     * @return
     */
    private int random25() {
        return random5() * 5 + random5();
    }

    private int random5() {
        Random random = new Random();
        return random.nextInt(5);
    }
}
