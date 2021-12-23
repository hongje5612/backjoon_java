package p1002;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1002
 */
public class Main {
    private static class TestCase {
        public int x1, y1, r1, x2, y2, r2;

        public TestCase(int x1, int y1, int r1, int x2, int y2, int r2) {
            this.x1 = x1;
            this.y1 = y1;
            this.r1 = r1;
            this.x2 = x2;
            this.y2 = y2;
            this.r2 = r2;
        }
    }

    private static TestCase[] testCases;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int t = kb.nextInt();

            testCases = new TestCase[t];

            for(int i = 0; i < t; i++) {
                int x1 = kb.nextInt();
                int y1 = kb.nextInt();
                int r1 = kb.nextInt();
                int x2 = kb.nextInt();
                int y2 = kb.nextInt();
                int r2 = kb.nextInt();

                testCases[i] = new TestCase(x1, y1, r1, x2, y2, r2);
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static int solve(TestCase t) {
        /*
        distance = (x1, y1) 과 (x2, y2) 의 거리
        distance2 = (int)Math.pow(distance, 2);
         */
        int distance2 = (int)Math.pow(t.x1 - t.x2, 2) + (int)Math.pow(t.y1 - t.y2, 2);
        int sum2 = (int)Math.pow(t.r1 + t.r2, 2);

        if(distance2 > sum2) return 0;
        else if(distance2 == sum2) return 1;
        else {
            int sub2 = (int)Math.pow(t.r1 - t.r2, 2);

            if(sub2 > distance2) return 0;
            else if(sub2 == distance2) return 1;
            else return 2;
        }
    }

    private static void printAnswer() {
        for(TestCase t : testCases) {
            if(t.x1 == t.x2 && t.y1 == t.y2 && t.r1 == t.r2) {
                System.out.println(-1);
            }
            else if(t.x1 == t.x2 && t.y1 == t.y2) {
                System.out.println(0);
            }
            else {
                System.out.println(solve(t));
            }
        }
    }

    public static void main(String[] args) {
        if(input()) printAnswer();
    }
}
