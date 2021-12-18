package p2156;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2156
 *
 *
 */
public class Main {
    private static short[] amounts;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            amounts = new short[n];

            for(int i = 0; i < n; i++) {
                amounts[i] = kb.nextShort();
            }

            state = true;
        }
        catch(Exception e) {
            state = false;
        }

        return state;
    }

    /**
     *
     * @param i
     * i 번째 포도주
     *
     * @param cnt
     * 0 마시지 않는 경우
     * 1 연속으로 첫 번째 잔으로 마시는 경우
     * 2 연속으로 두 번째 잔으로 마시는 경우
     *
     * @return
     * i 번째 포도주 왔을 경우  마시는 포도주 최대 량
     */
    private static int max(int i, int cnt) {
        if(i < 0) return 0;

        if(cnt == 0) {
            int v1 = max(i - 1, 0);
            int v2 = max(i - 1, 1);
            int v3 = max(i - 1, 2);

            return Math.max(v1, Math.max(v2, v3));
        }
        else if(cnt == 1) {
            return max(i - 1, 0) + amounts[i];
        }
        else if(cnt == 2) {
            return max(i - 1, 1) + amounts[i];
        }
        return -1;
    }

    private static int solve() {
        int index = amounts.length - 1;
        int v1 = max(index, 0);
        int v2 = max(index, 1);
        int v3 = max(index, 2);

        return Math.max(v1, Math.max(v2, v3));
    }

    public static void main(String[] args) {
        if(input()) System.out.println(solve());
    }
}
