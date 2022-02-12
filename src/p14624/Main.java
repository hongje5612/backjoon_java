package p14624;

import java.util.Scanner;

public class Main {
    private static String a_a(int n) {
        if(n == 0) return "*";
        else {
            StringBuilder sb = new StringBuilder();
            sb.append('*');
            for(int i = 0; i < 2 * n - 1; i++) sb.append(' ');
            sb.append('*');
            return sb.toString();
        }
    }
    private static void printMark(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append('*');
        }
        System.out.println(sb.toString());

        int half = n / 2;

        sb = new StringBuilder();
        for(int i = 0; i <= half; i++) {
            for(int j = 0; j < half - i; j++) sb.append(' ');

            sb.append(a_a(i));
            System.out.println(sb.toString());
            sb = new StringBuilder();
        }
    }

    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            if(n % 2 == 0) System.out.println("I LOVE CBNU");
            else {
                printMark(n);
            }
        }
    }
}
