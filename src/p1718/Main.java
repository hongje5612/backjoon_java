package p1718;

import java.util.Scanner;

public class Main {

    private static int[] orders(String key) {
        int[] answer = new int[key.length()];
        int index = 0;

        for(char c : key.toCharArray()) {
            answer[index++] = (int)c - (int)'a' + 1;
        }
        return answer;
    }

    private static String encode(String sentence, String key) {
        StringBuilder sb = new StringBuilder();
        int[] ds = orders(key);
        int index = 0;

        for(char c : sentence.toCharArray()) {
            if(c == ' ') sb.append(' ');
            else {
                int t = (int)c - ds[index];
                if(t < 'a') {
                    int d = 'a' - t - 1;
                    sb.append((char) ('z' - d));
                } else sb.append((char)t);
            }
            if(++index >= ds.length) index = 0;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            String sentence = kb.nextLine();
            String key = kb.nextLine();

            System.out.println(encode(sentence, key));
        } catch(Exception e) {

        }
    }
}
