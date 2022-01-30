package p2959;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static int solve(int a, int b, int c, int d) {
        Integer[] arr = { a, b, c, d };

        Arrays.sort(arr, Collections.reverseOrder());

        int side1 = Math.min(arr[0], arr[1]);
        int side2 = Math.min(arr[2], arr[3]);

        return side1 * side2;
    }

    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            int c = kb.nextInt();
            int d = kb.nextInt();

            System.out.println(solve(a, b, c, d));
        }
    }
}
