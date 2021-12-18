package p2156_1;

import java.util.Scanner;

public class Main {

    private static short[] amounts;

    private static boolean input() {
        boolean state;

        try (Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            amounts = new short[n];

            for (int i = 0; i < n; i++) {
                amounts[i] = kb.nextShort();
            }

            state = true;
        } catch (Exception e) {
            state = false;
        }

        return state;
    }
    
    private static int[][] dp;

    private static int max(int i, int cnt) {
        if(i < 0) return 0;
        if(dp[i][cnt] != -1) return dp[i][cnt];
        if(cnt == 0) {
            int v1 = max(i - 1, 0);
            int v2 = max(i - 1, 1);
            int v3 = max(i - 1, 2);

            return dp[i][cnt] = Math.max(v1, Math.max(v2, v3));
        }
        else if(cnt == 1) {
            return dp[i][cnt] = max(i - 1, 0) + amounts[i];
        }
        else if(cnt == 2) {
            return dp[i][cnt] = max(i - 1, 1) + amounts[i];
        }
        return -1;
    }

    private static int solve() {
        final int CASE = 3;

        dp = new int[amounts.length][CASE];

        for(int i = 0; i < amounts.length; i++) {
            for(int j = 0; j < CASE; j++) {
                dp[i][j] = -1;
            }
        }

        int index = amounts.length - 1;
        int v1 = max(index, 0);
        int v2 = max(index, 1);
        int v3 = max(index, 2);

        return Math.max(v1, Math.max(v2, v3));
    }

    public static void main(String[] args) {
        if(input()) System.out.println(solve());
    }
}
