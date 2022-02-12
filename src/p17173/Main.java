package p17173;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int n;
    private static List<Integer> k;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            k = new ArrayList<>();

            n = kb.nextInt();
            int m = kb.nextInt();

            for(int i = 0; i < m; i++) {
                int t = kb.nextInt();
                k.add(t);
            }

            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static int solve() {
        int answer = 0;

        for(int i = 1; i <= n; i++) {
            for(int t : k) {
                if(i % t == 0) {
                    answer += i;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        if(input()) System.out.println(solve());
    }
}
