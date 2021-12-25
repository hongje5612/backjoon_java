package p11727;

import java.util.Scanner;

/*
https://www.acmicpc.net/problem/11727
 */
public class Main {
    private static long[] dp;

    private static long solve(int n) {
        dp = new long[n + 1];

        for(int i = 0; i < n + 1; i++) {
            dp[i] = -1;
        }

        return f(n);
    }

    private static long f(int n) {
        if(dp[n] != -1) return dp[n];

        if(n == 1) return dp[n] = 1;
        else if(n == 2) return dp[n] = 3;
        else return dp[n] = (f(n - 1) + f(n - 2) * 2) % 10007;
    }

    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();
            System.out.println(solve(n));
        }
    }
}
