package p2579_1;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2579
 */
public class Main {
    private static short[] scores; // 계단에 적혀 있는 점수

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            scores = new short[n];

            for(int i = 0; i < n; i++) {
                scores[i] = kb.nextShort();
            }
            state = true;
        }
        catch (Exception e) {
            state = false;
        }

        return state;
    }

    private static int[][] dp; //다이나믹 프로그래밍을 위한 배열

    /*
    i 번째 계단을 1번 연속 해서 발은 경우와 2번 연속 해서 발은 경우의 점수의 최대값을 반환합니다.
     */
    private static int max(int i, int cnt) {
        if(i < 0) return 0;

        if(dp[i][cnt] != -1) return dp[i][cnt];

        if(cnt == 1) {
            int v1 = max(i - 2, 1) + scores[i];
            int v2 = max(i - 2, 2) + scores[i];
            return dp[i][cnt] = Math.max(v1, v2);
        }
        else if(cnt == 2) {
            return dp[i][cnt] = max(i - 1, 1) + scores[i];
        }
        return -1;
    }

    private static int solve() {
        final int CASE = 3; // 3 가지 경우가 있음

        dp = new int[scores.length][CASE];

        for(int i = 0; i < scores.length; i++) {
            for(int j = 0; j < CASE; j++) {
                dp[i][j] = -1;
            }
        }

        int v1 = max(scores.length - 1, 1);
        int v2 = max(scores.length - 1, 2);

        return Math.max(v1, v2);
    }

    public static void main(String[] args) {
        if(input()) System.out.println(solve());
    }
}
