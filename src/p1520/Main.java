package p1520;

import java.util.Scanner;

public class Main {
    private static final int[] d_row = { -1, 1, 0, 0 };
    private static final int[] d_col = { 0, 0, -1, 1 };

    private static short[][] map;

    private static int[][] dp; // 동적계획법

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int m = kb.nextInt(); // 세로 높이
            int n = kb.nextInt(); // 가로

            map = new short[m][n];

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    map[i][j] = kb.nextShort();
                }
            }

            state = true;
        } catch(Exception e) {
            state = false;
        }
        return state;
    }

    private static int solve() {
        dp = new int[map.length][map[0].length];

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        return f(map.length - 1, map[0].length - 1);
    }

    // row, col 까지 오는 경로의 갯 수
    private static int f(int row, int col) {
        if(dp[row][col] != -1) return dp[row][col];

        if(row == 0 && col == 0) return dp[row][col] = 1;
        else {
            int acc = 0;
            for(int i = 0; i < d_row.length; i++) {
                int r = row + d_row[i];
                int c = col + d_col[i];

                if(r >= 0 && r < map.length && c >= 0 && c < map[0].length && map[row][col] < map[r][c]) {
                    acc += f(r, c);
                }
            }
            return dp[row][col] = acc;
        }
    }

    public static void main(String[] args) {
        if(input()) {
            System.out.println(solve());
        }
    }
}
