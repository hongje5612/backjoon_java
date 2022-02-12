package p14623;

import java.util.Scanner;

public class Main {
    private static void printBinary(long n) {
        long rem = n % 2;
        long quot = n / 2;
        if(quot != 0) printBinary(quot);
        System.out.print(rem);
    }

    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            String b1 = kb.next();
            String b2 = kb.next();

            long n1 = Integer.parseInt(b1, 2);
            long n2 = Integer.parseInt(b2, 2);
            long result = n1 * n2;

            printBinary(result);
        }
    }
}
