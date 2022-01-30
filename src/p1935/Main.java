package p1935;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/1935
 */
public class Main {
    private static Map<Character, Integer> map = new HashMap<>();
    private static String formula;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int n = kb. nextInt();

            formula = kb.next();

            char c = 'A';
            for(int i = 0; i < n; i++) {
                int t = kb.nextInt();

                map.put(c, t);
                c++;
            }
            state = true;
        } catch(Exception e) {
            state = false;
        }
        return state;
    }

    private static double solve() {
        Stack<Object> stack = new Stack<>();

        for(char c : formula.toCharArray()) {
            if(Character.isUpperCase(c)) stack.push(c);
            else {
                Object a = stack.pop();
                Object b = stack.pop();

                double n1 , n2;

                if(a instanceof Double) n1 = (Double) a;
                else n1 = map.get(a);

                if(b instanceof Double) n2 = (Double) b;
                else n2 = map.get(b);

                switch (c) {
                    case '+' :
                        stack.push(n2 + n1);
                        break;
                    case '-' :
                        stack.push(n2 - n1);
                        break;
                    case '*' :
                        stack.push(n2 * n1);
                        break;
                    case '/' :
                        stack.push(n2 / n1);
                        break;
                }
            }
        }
        return (Double)stack.pop();
    }

    public static void main(String[] args) {
        if(input()) System.out.printf("%.02f\n", solve());
    }
}
