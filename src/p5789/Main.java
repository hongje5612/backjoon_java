package p5789;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> data;

    private static boolean input() {
        boolean state;

        data = new ArrayList<>();

        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            for(int i = 0; i < n; i++) {
                String t = kb.next();

                data.add(t);
            }
            state = true;
        } catch(Exception e) {
            state = false;
        }
        return state;
    }

    private static boolean solve(String s) {
        int half = s.length() / 2;
        return s.charAt(half - 1) == s.charAt(half);
    }

    public static void main(String[] args) {
        if(input()) {
            for(String s : data) {
                if(solve(s)) System.out.println("Do-it");
                else System.out.println("Do-it-Not");
            }
        }
    }
}
