package p11549;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner kb = new Scanner(System.in)) {
            int cnt = 0;
            int answer = kb.nextInt();

            for(int i = 0; i < 5; i++) {
                int guess = kb.nextInt();
                if(answer == guess) cnt++;
            }
            System.out.println(cnt);
        }
    }
}
