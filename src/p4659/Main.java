package p4659;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };

    private static boolean isVowel(char c) {
        for(char t : VOWELS) {
            if(c == t) return true;
        }
        return false;
    }

    /*
    모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
    모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
    같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
     */

    private static boolean test(String sentence) {
        int nv = 0; //모음의 개 수,number of vowels
        int cv = 0, cc = 0; // consecutive vowels, consecutive consonants
        Character prev = null;
        boolean bcv = false, bcc = false;

        for(char c : sentence.toCharArray()) {
            if(prev != null) {
                if(prev == c && c != 'e' && c!= 'o') return false;
            }

            if(isVowel(c)) {
                nv++;

                if(bcv) cv++;
                else {
                    bcv = true;
                    cv = 1;
                }
                cc = 0;
                bcc = false;
            } else {
                if(bcc) cc++;
                else {
                    bcc = true;
                    cc = 1;
                }
                cv = 0;
                bcv = false;
            }

            if(cc == 3 || cv == 3) return false;

            prev = c;
        }

        if(nv < 1) return false;

        return true;
    }

    private static List<String> data;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            data = new ArrayList<>();

            while(true) {
                String word = kb.next();
                if(word.equals("end")) break;
                data.add(word);
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }

        return state;
    }

    public static void main(String[] args) {
        if(input()) {
            for(String pw : data) {
                if(test(pw)) {
                    System.out.println("<" + pw + "> is acceptable.");
                } else {
                    System.out.println("<" + pw + "> is not acceptable." );
                }
            }
        }
    }
}
