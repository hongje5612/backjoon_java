package p5704;

import java.util.*;

public class Main {
    private static boolean isPangram(String s) {
        Set<Character> set = new HashSet<>();
        for(char c = 'a'; c <= 'z'; c++) {
            set.add(c);
        }

        for(char c : s.toCharArray()) {
            set.remove((Character)c);
        }

        return (set.size() == 0);
    }

    public static void main(String[] args) {
        List<String> l = new ArrayList<>();

        try(Scanner kb = new Scanner(System.in)) {
            while(true) {
                String s = kb.nextLine();
                if(s.equals("*")) break;
                l.add(s);
            }

            for(String s : l) {
                if(isPangram(s)) System.out.println("Y");
                else System.out.println("N");
            }
        }
    }
}
