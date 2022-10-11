package graph.bfs;

import java.util.*;

public class InfectionSequence {

    /**
     * You're give a number n to represent the total number of houses, and a list infected to represent the houses initially infected. Infected houses cannot be reinfected. An infected house at index X
     * can infect the house at X + 1 and X - 1 on the next day. Return the number of distinct possible infection sequences.
     * <p>
     * Example: n= 6  infected =[3 5]  return 6
     *
     * @param n
     * @param infected
     * @return
     */
    public int infectedSequence(int n, int[] infected) {
        /*
              We can use BFS to solve the problem
              As long as we can know the infected groups for each day, then the final result would be the multiplication of the permutation of infected houses for each day
              e.g.
              3  5
              2  4  6
              1

              Data Structure:

              1. Set<Integer> infected to store all the infected houses
              2. Queue<Integer> queue to keep all the infected houses on each day (This will be used to generate the infected houses for the next day)
         */

        if (n == 0 || infected == null || infected.length == 0) {
            return 0;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> infectedHouses = new HashSet<>();
        for (int num : infected) {
            queue.offer(num);
            infectedHouses.add(num);
        }
        int[] directions = {-1, 1};
        int count = 1;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size(); //size also represents how many houses infected on each day.
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
                for (int direction : directions) {
                    int neighbor = num + direction;
                    if (validNum(neighbor, n) && infectedHouses.add(neighbor)) {
                        queue.offer(neighbor);
                    }
                }
            }
            if (level == 0) {
                level++;
                continue;
            }
            count *= permutation(size);
            level++;
        }
        return count;
    }

    private int permutation(int n) {
        if (n == 1) {
            return 1;
        }
        return n * permutation(n - 1);
    }

    private boolean validNum(int num, int n) {
        return num >= 1 && num <= n;
    }

    public static void main(String[] args) {
        InfectionSequence infectionSequence = new InfectionSequence();
        int[] infected = {3, 5};
        System.out.println(infectionSequence.infectedSequence(6, infected));
    }
}
