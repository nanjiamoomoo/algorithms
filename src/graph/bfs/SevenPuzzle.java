package graph.bfs;

import java.util.*;

public class SevenPuzzle {

    /**
     * Given eight cards with number 0, 1, 2, ..7 on them, the cards are placed in two rows with 4 cards in each row. In each step only card 0 could swap with one of its adjacent(top, right, bottom, left) card. Your goal is to make all cards placed in order like this:
     *
     * 0 1 2 3
     * 4 5 6 7
     *
     * Find the minimum number of steps from the given state to the final state. If there is no way to the final state, then return -1.
     *
     * The state of cards is represented by an array of integer, for example [0,1,2,3,4,5,6,7] where the first four numbers are in the first row from left to right while the others are placed in the second row from left to right.
     *
     * Example:
     *
     * Input: [4,1,2,3,5,0,6,7]       Output: 2
     *
     * Explanation:
     *
     * Initial state is:
     *
     * 4 1 2 3
     * 5 0 6 7
     *
     * First swap 0 with 5, then the state is:
     *
     * 4 1 2 3
     * 0 5 6 7
     *
     * Then swap 0 with 4, then we get the final state:
     *
     * 0 1 2 3
     * 4 5 6 7
     *
     * @param values the representation of the state of cards as an array
     * @return the minimum number of steps
     */
    public static final int ROW = 2;
    public static final int COL = 4;
    public static final Board FINAL_BOARD = new Board(new int[][]{{0, 1, 2, 3}, {4, 5, 6, 7}});
    public static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static class Board {
        int[][] board;
        public Board() {
            board = new int[ROW][COL];
        }
        public Board(int[][] board) {
            this();
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    this.board[i][j] = board[i][j];
                }
            }
        }
        public Board(int[] values) {
            this();
            for (int i = 0; i < values.length; i++) {
                this.board[i / COL][i % COL] = values[i];
            }
        }
        public int[] getZero() {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (board[i][j] == 0) {
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Board)) {
                return false;
            }
            Board another = (Board) o;
            for ( int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (board[i][j] != another.board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        public int hashCode() {
            int code = 0;
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    code = 10 * code + board[i][j];
                }
            }
            return code;
        }

        public static boolean valid(int x, int y) {
            return x >= 0 && x < ROW && y >= 0 && y < COL;
        }
        public void swap(int x1, int y1, int x2, int y2) {
            int tmp = board[x1][y1];
            board[x1][y1] = board[x2][y2];
            board[x2][y2] = tmp;
        }

    }
    public int sevenPuzzle(int[] values) {
        /*
               we can define a class board{} as the state
               the initial state is int[] values (converts to 2D for easier process
               the final state is {0, 1, 2, 3},
                                  {4, 5, 6, 7}

               we can use BFS algorithm to traverse all the states that we can transform from the initial state
               e.g.
               4, 1, 2, 3
               5, 0, 6, 7

               can be converted into

               4, 0, 2, 3
               5, 1, 6, 7
               or
               4, 1, 2, 3
               0, 5, 6, 7
               or
               4, 1, 2, 3
               5, 6, 0, 7

               As long as the final state is reachable, we return the steps on the way
               otherwise we return -1;

         */

        Queue<Board> queue = new ArrayDeque<>();
        Set<Board> visited = new HashSet<>();
        Board iniBoard = new Board(values);
        if (iniBoard.equals(FINAL_BOARD)) {
            return 0;
        }
        queue.offer(iniBoard);
        visited.add(iniBoard);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Board curr = queue.poll();
                int[] zero = curr.getZero();
                int zeroX = zero[0];
                int zeroY = zero[1];
                for (int[] dir : DIRS) {
                    int x = zeroX + dir[0];
                    int y = zeroY + dir[1];
                    if (Board.valid(x, y)) {
                        curr.swap(zeroX, zeroY, x, y);
                        if (curr.equals(FINAL_BOARD)) {
                            return step;
                        }
                        Board newBoard = new Board(curr.board);
                        if (visited.add(newBoard)) {
                            queue.offer(newBoard);
                        }
                        //swap it back
                        curr.swap(zeroX, zeroY, x, y);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] values = {2,4,3,6,1,7,5,0};
        SevenPuzzle sevenPuzzle = new SevenPuzzle();
        System.out.println(sevenPuzzle.sevenPuzzle(values));
    }

}
