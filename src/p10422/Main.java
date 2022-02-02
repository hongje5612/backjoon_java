package p10422;

import java.util.Scanner;

public class Main {
    /**
     *
     * @param k
     * @return
     * 1~k까지 합을 반환합니다.
     */
    private static int sum(int k) {
        int half = k / 2;

        if(k % 2 == 0) return half * (k + 1);
        else return half * (k + 1) + (half + 1);
    }
    /**
     *
     * @param n 공의 갯 수
     * @param k 바구니의 갯 수
     * @return
     */
    private static int solve(int n, int k) {
        if(n <= k) return -1;

        int d = sum(k);

        n -= k;

        int quot = n / d;
        int rem = n % d;

        if(quot == 0) return -1;

        if(rem == 0) return k - 1;
        else return k;
    }

    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();
            int k = kb.nextInt();

            System.out.println(solve(n, k));
        }
    }
}
