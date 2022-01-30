package p1987;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static char[][] board;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int r = kb.nextInt();
            int c = kb.nextInt();

            board = new char[r][c];

            for(int i = 0; i < r; i++) {
                String t = kb.next();
                for(int j = 0; j < t.length(); j++) {
                    board[i][j] = t.charAt(j);
                }
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static int solve() {
        return new Solver(board).getAnswer();
    }

    private static class Solver {
        private static final int[] d_row = { -1, 1, 0, 0 };
        private static final int[] d_col = { 0, 0, -1, 1 };

        private char[][] board;
        private boolean[][] visited;
        private Set<Character> set;
        private int answer;

        public Solver(char[][] arr) {
            board = arr;
            visited = new boolean[arr.length][arr[0].length];
            set = new HashSet<>();
        }

        public int getAnswer() {
            answer = 0;
            dfs(0, 0);
            return answer;
        }

        private void dfs(int row, int col) {
            visited[row][col] = true;
            set.add(board[row][col]);

            if(set.size() > answer) answer = set.size();

            for(int i = 0; i < d_row.length; i++) {
                int r = row + d_row[i];
                int c = col + d_col[i];

                if(r >= 0 && r < board.length && c >= 0 && c < board[0].length && !visited[r][c] && !set.contains(board[r][c])) {
                    dfs(r, c);
                }
            }
            set.remove(board[row][col]);
            visited[row][col] = false;
        }
    }

    public static void main(String[] args) {
        if(input()) {
            System.out.println(solve());
        }
    }
}
