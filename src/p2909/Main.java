package p2909;

import java.util.Scanner;

public class Main {
    private static int solve(int c, int k) {
        double t = Math.pow(10, k);

        return (int)(Math.round(c / t) * t);
    }

    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            int c = kb.nextInt();
            int k = kb.nextInt();

            System.out.println(solve(c, k));
        }
    }
}
