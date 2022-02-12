package p7490;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final char[] CHARS = { ' ', '+', '-' };
    private static char[] arr;

    private static int parse(String s) {
        StringBuilder sb = new StringBuilder();
        Character operator = null;
        int result = 0;

        for(char c : s.toCharArray()) {
            if(c == '+' || c == '-') {
                if(operator == null) {
                    result = Integer.parseInt(sb.toString());
                } else if(operator == '+') {
                    result += Integer.parseInt(sb.toString());
                } else if(operator == '-') {
                    result -= Integer.parseInt(sb.toString());
                }
                operator = c;
                sb = new StringBuilder();
            }
            else if(c == ' ') ;
            else {
                sb.append(c);
            }
        }

        if(operator == null) result = Integer.parseInt(sb.toString());
        else if(operator == '+') result += Integer.parseInt(sb.toString());
        else if(operator == '-') result -= Integer.parseInt(sb.toString());

        return result;
    }

    private static void r(int n) {
        if(n == arr.length - 1) {
            for(int i = 0; i < CHARS.length; i++) {
                arr[n] = CHARS[i];

                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < arr.length; j++) {
                    sb.append(j + 1);
                    sb.append(arr[j]);
                }
                sb.append(arr.length + 1);

                String expression = sb.toString();
                if(parse(expression) == 0) System.out.println(expression);
            }
        } else {
            for(int i = 0; i < CHARS.length; i++) {
                arr[n] = CHARS[i];
                r(n + 1);
            }
        }
    }

    private static void solve(int n) {
        arr = new char[n - 1];

        r(0);
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();

        try(Scanner kb = new Scanner(System.in)) {
            int t = kb.nextInt(); // test case

            for(int i = 0; i < t; i++) {
                int n = kb.nextInt();
                l.add(n);
            }

            for(int n : l) {
                solve(n);
                System.out.println();
            }
        }
    }
}
