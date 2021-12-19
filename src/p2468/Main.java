package p2468;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2468
 */
public class Main {
    private static class Zone {
        private static final byte OVERFLOW = -1;

        /**
         * 영역의 높이 정보를 담고 있는 배열입니다.
         */
        private byte[][] arr;

        public Zone(byte[][] arr) {
            this.arr = arr;
        }

        /***
         * 복사 생성자
         * @param other
         */
        public Zone(Zone other) {
            arr = new byte[other.arr.length][];

            for(int i = 0; i < other.arr.length; i++) {
                arr[i] = Arrays.copyOf(other.arr[i], other.arr[i].length);
            }
        }

        /**
         * 물에 잠기는 영역을 OVERFLOW(-1) 로 체크합니다.
         * @param level
         * 물에 잠기는 높이
         */
        public void overflow(byte level) {
            for(int r = 0; r < arr.length; r++) {
                for(int c = 0; c < arr[r].length; c++) {
                    if(arr[r][c] <= level) arr[r][c] = OVERFLOW;
                }
            }
        }

        private static final byte VISITED = -2;
        private static final int[] dr = { -1, 1, 0, 0 };
        private static final int[] dc = { 0, 0, -1, 1 };

        /**
         * 물에 잠기지 않는 한 영역을 깊이우선탐색하면서 방문체크를 합니다.
         * @param row
         * 지역의 행 위치
         * @param col
         * 지역의 열 위치
         */
        private void dfs(int row, int col) {
            if(row < 0 || row >= arr.length) return;
            else if(col < 0 || col >= arr[0].length) return;
            else {
                if(arr[row][col] == VISITED) return;
                if(arr[row][col] == OVERFLOW) return;

                arr[row][col] = VISITED;

                for(int i = 0; i < dr.length; i++) {
                    dfs(row + dr[i], col + dc[i]);
                }
            }
        }

        /**
         * 안전한 영역의 갯수를 카운트 합니다.
         * @return
         * 안전한 영역의 갯 수를 반환합니다.
         */
        public int count() {
            int answer = 0;

            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[i].length; j++) {
                    if(arr[i][j] >= 0) {
                        answer++;
                        dfs(i, j);
                    }
                }
            }
            return answer;
        }

        public void show() {
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static byte[][] arr;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int n = kb.nextInt();

            arr = new byte[n][n];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    arr[i][j] = kb.nextByte();
                }
            }
            state = true;
        }
        catch (Exception e) {
            state = false;
        }
        return state;
    }

    /**
     * 수위를 높이면서 안전한 영역의 갯 수의 최대값을 찾습니다.
     * @return
     * 안전한 영역의 최대 갯 수를 반환합니다.
     */
    private static int solve() {
        final byte LOW_LEVEL = 0;
        final byte TOP_LEVEL = Byte.MAX_VALUE;

        Zone zone = new Zone(arr);

        int answer = Integer.MIN_VALUE;

        for(int level = LOW_LEVEL; level <= TOP_LEVEL; level++) {
            Zone tZone = new Zone(zone);

            tZone.overflow((byte)level);

            int n = tZone.count();
            if(n > answer) answer = n;
        }
        return answer;
    }

    public static void main(String[] args) {
        if(input()) System.out.println(solve());
    }
}
