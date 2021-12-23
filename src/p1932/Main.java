package p1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1932
 */
public class Main {
    private static short[][] arr;   // 입력 데이타
    private static int[][] dp;      //다이나믹 프로그램을 위한 배열

    /**
     * 데이타 입력 받기
     * @return
     * 입력 받는 동안 문제가 생기면 false
     * 정상적으로 입력을 받으면 true
     */
    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(kb.readLine()); // 삼각형의 크기

            arr = new short[n][];

            for(int i = 0; i < n; i++) {
                arr[i] = new short[i + 1];

                String[] ss = kb.readLine().split("\\s+");

                int index = 0;
                for(String s : ss) {
                    arr[i][index++] = Short.parseShort(s);
                }
            }
            state = true;
        } catch(IOException e) {
            state = false;
        } catch(Exception e) {
            state = false;
        }
        return state;
    }

    /**
     * 다이나믹 프로그래밍
     * 순환식 점화식
     * @param i
     * 행 번호
     * @param j
     * 열 번호
     * @return
     * i 행 j 열의 합의 최대값을 반환합니다.
     */
    private static int max(int i, int j) {
        if(dp[i][j] != -1) return dp[i][j];

        if(i == 0 && j == 0) return dp[i][j] = arr[i][j];

        if(j - 1 < 0) return dp[i][j] = max(i - 1, j) + arr[i][j];
        else if(j == arr[i].length - 1) return dp[i][j] = max(i - 1, j - 1) + arr[i][j];
        else return dp[i][j] = Math.max(max(i - 1, j - 1), max(i - 1, j)) + arr[i][j];
    }

    /**
     *
     * @return
     * 문제의 정답을 반환합니다.
     */
    private static int solve() {
        dp = new int[arr.length][];

        for(int i = 0; i < arr.length; i++) {
            dp[i] = new int[i + 1];
            for(int j = 0; j < arr[i].length; j++) {
                dp[i][j] = -1;
            }
        }

        int answer = Integer.MIN_VALUE;

        for(int col = 0; col < arr[arr.length - 1].length; col++) {
            int value = max(arr.length -1, col);

            if(value > answer) answer = value;
        }
        return  answer;
    }


    public static void main(String[] args) {
        if(input()) {
            System.out.println(solve());
        }
    }
}
