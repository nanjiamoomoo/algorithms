import java.util.Random;

public class GenerateRandomMaze {

    /**
     * Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. For each pair of cells on the corridor, there must exist one and only one path between them. (Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.). The wall is denoted by 1 in the matrix and corridor is denoted by 0.
     * Assumption:
     *  N = 2K + 1 and K >= 0
     *  the top left corner must be corridor
     *  there should be as many corridor cells as possible
     *  for each pair of cells on the corridor, there must exist one and only one path between them
     * Examples:
     *  N = 5, one possible maze generated is
     *  0  0  0  1  0
     *  1  1  0  1  0
     *  0  1  0  0  0
     *  0  1  1  1  0
     *  0  0  0  0  0
     * @param n
     * @return
     */
    public int[][] generateRandomMaze(int n) {
        /*
            How to generate a maze?
            We generate a maze with int[n][n], with [0][0] position as 0, all other positions as 1
            Then we use DFS
            on each recursion level,
                there are four directions to move,
                on each direction, move 2 steps and if the next position is 1, then we can safely change the path to 0

             How to randomly generate the maze?
             we can use shuffle the four directions and make sure each recursion level, we traverse four directions with different sequence.
         */

        int[][] maze = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    maze[i][j] = 0;
                } else {
                    maze[i][j] = 1;
                }
            }
        }

        generateMaze(maze, 0, 0);
        return maze;
    }

    private void generateMaze(int[][] maze, int x, int y) {
        Dir[] dirs = Dir.values();
        shuffles(dirs);
        for (Dir dir : dirs) {
            int x2 = dir.moveX(x, 2);
            int y2 = dir.moveY(y, 2);
            if (isValidWall(maze, x2, y2)) {
                int x1 = dir.moveX(x, 1);
                int y1 = dir.moveY(y, 1);
                maze[x2][y2] = 0;
                maze[x1][y1] = 0;
                generateMaze(maze, x2, y2);
            }
        }
    }

    private boolean isValidWall(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
    }

    private void shuffles(Dir[] dirs) {
        /*
            for each position i, we need to randomly choose j from i ~ array.length - 1 and swap with i position
            i ~ array.length - 1 -> i + (0 ~ array.length - i - 1) -> random.nextInt(array.length - i) + i
         */

        Random random = new Random();
        for (int i = 0; i < dirs.length; i++) {
            int j = random.nextInt(dirs.length - i) + i;
            Dir tmp = dirs[i];
            dirs[i] = dirs[j];
            dirs[j] = tmp;
        }
    }

    enum Dir {
        North(-1, 0),
        South(1, 0),
        East(0, 1),
        West(0, -1);

        private int deltaX;
        private int deltaY;

        Dir(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        public int moveX(int x, int times) {
            return x + deltaX * times;
        }

        public int moveY(int y, int times) {
            return y + deltaY * times;
        }
    }

    public static void main(String[] args) {
        GenerateRandomMaze generateRandomMaze = new GenerateRandomMaze();
        generateRandomMaze.generateRandomMaze(3);

    }

}
