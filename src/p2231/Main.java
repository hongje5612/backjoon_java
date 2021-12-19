package p2231;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2231
 */
public class Main {
    private static int f(int n) {
        String s = String.valueOf(n);

        int sum = n;

        for(char c : s.toCharArray()) {
            sum += (int)(c - '0');
        }
        return sum;
    }

    private static Integer solve(int n) {
        Integer answer = null;

        for(int i = n - 1; i > 0; i--) {
            if(f(i) == n) answer = i;
        }
        return answer;
    }

    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            Integer answer = solve(n);

            if(answer == null) System.out.println(0);
            else System.out.println(answer);
        }
    }
}
