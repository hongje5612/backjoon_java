package p1918;

import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/1918
 */
public class Main {

    private static boolean isOperator(String s) {
        String[] op = { "+", "-", "*", "/" };

        for(String t : op) {
            if(s.equals(t)) return true;
        }
        return false;
    }

    /**
     *
     * @param iStack
     * 알파벳 문자열과 사칙연산자{"+", "-", "*", "/"}로 만을 담고 있는 스택
     * @return
     * postfix notation(후위 표기식)으로 표현된 문자열을 반환합니다.
     */
    private static String parse(Stack<String> iStack) {
        String t = iStack.peek();
        if(isOperator(t)) return null;

        Stack<String> stack = new Stack<>();
        boolean found = false;

        while(!iStack.isEmpty()) {
            String c = iStack.pop();
            switch (c) {
                case "*":
                case "/":
                    if(iStack.empty()) return null;
                    if (!found) found = true;
                    else return null;
                    stack.push(c + "");
                    break;
                default:
                    if (found) {
                        String s1, s2;
                        if (!stack.isEmpty()) s2 = stack.pop();
                        else return null;
                        if (!stack.isEmpty()) s1 = stack.pop();
                        else return null;
                        StringBuffer sb = new StringBuffer();
                        sb.append(s1);
                        sb.append(c + "");
                        sb.append(s2);
                        stack.push(sb.toString());
                        found = false;
                    } else {
                        stack.push(c + "");
                    }
            }
        }

        Stack<String> stack1 = new Stack<>();

        while(!stack.isEmpty()) {
            stack1.push(stack.pop());
        }

        Stack<String> stack2 = new Stack<>();
        found = false;

        while(!stack1.isEmpty()) {
            String s3 = stack1.pop();
            switch (s3) {
                case "+" :
                case "-" :
                    if(!found) found = true;
                    else return null;
                    stack2.push(s3);
                    break;
                default:
                    if(found) {
                        String s1, s2;
                        if (!stack2.isEmpty()) s2 = stack2.pop();
                        else return null;
                        if (!stack2.isEmpty()) s1 = stack2.pop();
                        else return null;
                        StringBuffer sb = new StringBuffer();
                        sb.append(s1);
                        sb.append(s3);
                        sb.append(s2);
                        stack2.push(sb.toString());
                        found = false;
                    } else stack2.push(s3);
            }
        }
        return stack2.pop();
    }

    /**
     *
     * @param s
     * 알파벳과 사칙연산자{'+', '-', '*', '/'} 그리고 {'(', ')'}로 만 이루어진 문자열
     * @return
     * postfix notation(후위 표기식)으로 표현된 문자열을 반환합니다.
     */
    private static String parse(String s) {
        Stack<String> stack = new Stack<>();
        Stack<String> stack1 = new Stack<>();

        for(char c : s.toCharArray()) {
            switch (c) {
                case ')' :
                    Stack<String> stack2 = new Stack<>();
                    boolean found = false;
                    while(true) {
                        String t;
                        if(!stack.isEmpty()) t = stack.pop();
                        else return null;
                        if(t.equals("(")) {
                            found = true;
                            break;
                        }
                        else stack2.push(t);
                    }
                    if(!found) return null;
                    stack.push(parse(stack2));
                    break;
                default :
                    stack.push(c + "");
            }
        }

        while(!stack.isEmpty()) {
            stack1.push(stack.pop());
        }

        return parse(stack1);
    }


    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            String str = kb.next();

            System.out.println(parse(str));
        }
    }

}
