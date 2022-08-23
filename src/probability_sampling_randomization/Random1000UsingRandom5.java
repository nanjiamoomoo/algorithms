package probability_sampling_randomization;

import java.util.Random;

public class Random1000UsingRandom5 {
    /**
     * return random number from 0 ~ 999
     * @return
     */
    public int random1000() {
        while (true) {
            int r = random3125();
            if (r < 3000) {
                return r % 1000;
            }
        }
    }

    /**
     * return random number from 0 ~ 5 ^ 5
     * @return
     */
    private int random3125() {
        int random = 0;
        for (int i = 0; i < 5; i++) {
            random = random * 5 + random5();
        }
        return random;
    }

    private int random5() {
        Random random = new Random();
        return random.nextInt(5);
    }
}
