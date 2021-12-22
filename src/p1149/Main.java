package p1149;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1149
 *
 * 순환식(점화식)만으로 구성되었습니다.
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

    private static Cost[] costs;

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

    private static int min(int i, int color) {
        if(i < 0) return 0;

        switch (color) {
            case 0 :
                return Math.min(min(i - 1, 1), min(i - 1, 2)) + costs[i].r;
            case 1:
                return Math.min(min(i - 1, 0), min(i - 1, 2)) + costs[i].g;
            case 2 :
                return Math.min(min(i - 1, 0), min(i - 1, 1)) + costs[i].b;
        }
        return -1; // never execute
    }

    private static int solve() {
        int index = costs.length - 1;
        int t = Math.min(min(index, 0), min(index, 1));
        return Math.min(t, min(index, 2));
    }

    public static void main(String[] args) {
        if(input()) System.out.println(solve());
    }
}
