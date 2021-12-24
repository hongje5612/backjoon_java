package p1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/1010
 */
public class Main {

    private static class Combination {
        private static final int SIZE = 30;

        private static long[][] dp;

        static {
            dp = new long[SIZE][SIZE];

            for(int i = 0; i < SIZE; i++) {
                for(int j = 0; j < SIZE; j++) {
                    dp[i][j] = -1;
                }
            }
        }

        /**
         * 조합 경우의 수를 반환합니다.
         * @param n
         * 0 < n < 30
         * @param r
         * 0 < r <= n < 30
         * @return
         * n개 중 r개를 선택하는 방법의 수를 반환합니다.
         */
        public static long getValue(int n, int r) {
            if(dp[n][r] != -1) return dp[n][r];

            if(n == r) return dp[n][r] = 1;
            if(r == 1) return n;

            return dp[n][r] = getValue(n - 1, r - 1) + getValue(n - 1, r);
        }
    }

    private static class Site {
        public int west; // 서쪽에 있는 사이트 수
        public int east; // 동쪽에 있는 사이트 수

        public Site(int west, int east) {
            this.west = west;
            this.east = east;
        }
    }

    private static List<Site> siteList;

    private static boolean input() {
        boolean state;

        siteList = new ArrayList<>();

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.parseInt(kb.readLine());

            for(int i = 0; i < t; i++) {
                String[] ss = kb.readLine().split("\\s+");

                int west = Integer.parseInt(ss[0]);
                int east = Integer.parseInt(ss[1]);

                siteList.add(new Site(west, east));
            }
            state = true;
        } catch (IOException e) {
            state = false;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    public static void main(String[] args) {
        if(input()) {
            for(var site : siteList) {
                System.out.println(Combination.getValue(site.east, site.west));
            }
        }
    }
}
