package p3613;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static String toCPlusPlus(String s) {
        StringBuilder sb = new StringBuilder();

        for(char c : s.toCharArray()) {
            if(Character.isUpperCase(c)) {
                sb.append('_');
                sb.append(Character.toLowerCase(c));
            }
            else sb.append(c);
        }
        return sb.toString();
    }

    private static String toJava(String s) {
        StringBuilder sb = new StringBuilder();
        boolean appear = false;

        for(char c : s.toCharArray()) {
            if(appear) {
                if(c != '_') {
                    sb.append(Character.toUpperCase(c));
                    appear = false;
                }
            } else {
                if(c == '_') {
                    appear = true;
                } else {
                    sb.append(c);
                }
            }
        }

        char c = sb.charAt(0);
        if(Character.isUpperCase(c)) {
            sb.deleteCharAt(0);
            sb.insert(0, Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String cplusplus_pattern = "_*[a-z]+[_+[a-z]+]*";
        String java_pattern = "[a-z]+[[A-Z]{1}[a-z]*]*";

        try(Scanner kb = new Scanner(System.in)) {
            String s = kb.next();

            if (Pattern.matches(cplusplus_pattern, s)) {
                System.out.println(toJava(s));
            } else if (Pattern.matches(java_pattern, s)) {
                System.out.println(toCPlusPlus(s));
            } else System.out.println("Error!");
        }
    }
}
