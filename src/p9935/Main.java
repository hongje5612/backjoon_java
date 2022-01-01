package p9935;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/9935
 */
public class Main {

    private static class Pair {
        public char ch;
        public int th; // bomb 안에서 몇 번째 원소, bomb 안의 문자가 아니면 -1

        public Pair(char ch, int th) {
            this.ch = ch;
            this.th = th;
        }
    }

    private static String debomb(String string, String bomb) {
        char[] bombChars = bomb.toCharArray();

        if(bombChars.length > 1) {
            Stack<Pair> stack = new Stack<>();

            for(char c : string.toCharArray()) {
                if(!stack.isEmpty()) {
                    Pair p = stack.peek();
                    if(p.th == -1) {
                        if(c == bombChars[0]) stack.push(new Pair(c, 0));
                        else stack.push(new Pair(c, -1));
                    } else {
                        if(c == bombChars[p.th + 1]) {
                            stack.push(new Pair(c, p.th + 1));

                            if(stack.peek().th == bombChars.length - 1) {
                                for(int i = 0; i < bombChars.length; i++) {
                                    stack.pop();
                                }
                            }
                        }
                        else {
                            if(c == bombChars[0]) stack.push(new Pair(c, 0));
                            else stack.push(new Pair(c, -1));
                        }
                    }
                }
                else {
                    if(c == bombChars[0]) stack.push(new Pair(c, 0));
                    else stack.push(new Pair(c, -1));
                }
            }

            StringBuilder sb = new StringBuilder(stack.size());
            while(!stack.isEmpty()) {
                sb.append(stack.pop().ch);
            }
            sb.reverse();

            return sb.toString();
        }
        else {
            StringBuilder sb = new StringBuilder();
            char charBomb = bomb.charAt(0);

            for(char c : string.toCharArray()) {
                if(c != charBomb) sb.append(c);
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            String string = kb.readLine();
            String bomb = kb.readLine();

            String answer = debomb(string, bomb);

            if(answer.length() == 0) System.out.println("FRULA");
            else System.out.println(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
