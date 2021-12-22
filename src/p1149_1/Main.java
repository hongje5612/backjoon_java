package p1149_1;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1149
 *
 * dynamic programming
 */
public class Main {
    private static class Cost {
        public int r, g, b;

        public Cost(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    private static final int NUMBER_OF_COLORS = 3;

    private static Cost[] costs;
    private static int[][] dp; // 동적계획법에 사용되는 배열

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            costs = new Cost[n];

            for(int i = 0; i < n; i++) {
                int r, g, b;
                r = kb.nextInt();
                g = kb.nextInt();
                b = kb.nextInt();

                costs[i] = new Cost(r, g, b);
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    /**
     * i 번째 집을 color로 칠 했을 경우 최소값을 반환합니다.
     * @param i
     * i 번째 집
     * @param color
     * 색깔  0 인 경우 red, 1 인 경우 green, 2 인 경우  blue
     * @return
     * 최소값을 반환합니다.
     */
    private static int min(int i, int color) {
        if(i < 0) return 0;

        if(dp[i][color] != -1) return dp[i][color];

        switch (color) {
            case 0 :
                return dp[i][color] = Math.min(min(i - 1, 1), min(i - 1, 2)) + costs[i].r;
            case 1:
                return dp[i][color] = Math.min(min(i - 1, 0), min(i - 1, 2)) + costs[i].g;
            case 2 :
                return dp[i][color] = Math.min(min(i - 1, 0), min(i - 1, 1)) + costs[i].b;
        }
        return -1; // never execute
    }

    private static int solve() {
        dp = new int[costs.length][NUMBER_OF_COLORS];

        for(int i = 0; i < costs.length; i++) {
            for(int j = 0; j < NUMBER_OF_COLORS; j++) {
                dp[i][j] = -1;
            }
        }
        int index = costs.length - 1;
        int t = Math.min(min(index, 0), min(index, 1));
        return Math.min(t, min(index, 2));
    }

    public static void main(String[] args) {
        if(input()) System.out.println(solve());
    }
}

