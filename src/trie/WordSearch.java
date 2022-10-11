package trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    /**
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     *
     * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     *
     * For example,
     * Given words = ["oath","pea","eat","rain"] and board =
     *
     * [
     *   ['o','a','a','n'],
     *   ['e','t','a','e'],
     *   ['i','h','k','r'],
     *   ['i','f','l','v']
     * ]
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
         /*
      Step1: build trieTree based on the dictionary
      Step2: from each position in the word, use DFS to find the all the words in the dictionart
    */

        Set<String> set = new HashSet<>();
        if (board == null || words == null || words.length == 0 || board.length == 0 || board[0].length == 0) {
            return new ArrayList<String>();
        }
        TrieNode root = buildTrieTree(words);
        boolean[][] visited = new boolean[board.length][board[0].length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(root, board, i, j, set, sb, visited);
            }
        }
        List<String> list = new ArrayList<>();
        for (String s : set) {
            list.add(s);
        }
        return list;
    }

    private void dfs(TrieNode node, char[][] board, int x, int y, Set<String> res, StringBuilder sb, boolean[][] visited) {
        //return if out of bound
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
            return;
        }
        TrieNode next = node.children[board[x][y] - 'a'];
        if (next == null) {
            return ;
        }
        visited[x][y] = true;
        sb.append(board[x][y]);
        if (next.isWord) {
            res.add(sb.toString());
        }
        for (int[] dir : DIRS) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            dfs(next, board, nextX, nextY, res, sb, visited);
        }
        sb.deleteCharAt(sb.length() - 1);
        visited[x][y] = false;
    }



    public static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private TrieNode buildTrieTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                TrieNode next = curr.children[word.charAt(i) - 'a'];
                if (next == null) {
                    next = new TrieNode();
                    curr.children[word.charAt(i) - 'a'] = next;
                }
                curr = next;
            }
            curr.isWord = true;
        }
        return root;
    }

}
