package p3197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/3197
 *
 * 시간초과로 탈락되었습니다. 방법을 찾고 있습니다.
 */
public class Main {

    private static class Location {
        public int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Location)) return false;
            Location location = (Location)o;
            return row == location.row && col == location.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static class Lake {
        private static final char SWAN = 'L';
        private static final char WATER = '.';
        private static final char ICE = 'X';
        private static final int NUMBER_OF_SWANS = 2;

        private static final int[] D_ROW = { -1, 1, 0, 0 };  //상하좌우
        private static final int[] D_COL = { 0, 0, -1, 1 };  //상하좌우

        private static final char VISITED = 'V';

        private char[][] map;
        private Location[] locOfSwans;

        public Lake(char[][] map) {
            this.map = map;
            locOfSwans = new Location[NUMBER_OF_SWANS];

            int index = 0;
            int row = 0;
            for(char[] arr : map) {
                int col = 0;
                for(char c : arr) {
                    if(map[row][col] == SWAN) {
                        locOfSwans[index++] = new Location(row, col);
                        map[row][col] = WATER;
                    }
                    col++;
                }
                row++;
            }
        }

        /**
         * 만약에 얼음이 물과 접촉하는 경우
         * @param row
         * 행 번호
         * @param col
         * 열 번호
         * @return
         * 얼음이 물과 접촉하는 경우 참을 반환합니다.
         */
        private boolean inContackWithWater(int row, int col) {
            for(int i = 0; i < D_ROW.length; i++) {
                int r = row + D_ROW[i];
                int c = col + D_COL[i];

                if(r >= 0 && r < map.length && c >= 0 && c < map[0].length) {
                    if(map[r][c] == WATER) return true;
                }
            }
            return false;
        }

        /**
         * 하루가 지난 후의 얼음 상태로 변경합니다.
         */
        public void aDayPasses() {
            char[][] m = new char[map.length][map[0].length];

            for(int row = 0; row < map.length; row++) {
                for(int col = 0; col < map[0].length; col++) {
                    if(map[row][col] == ICE) {
                        if(inContackWithWater(row, col)) m[row][col] = WATER;
                        else m[row][col] = map[row][col];
                    }
                    else m[row][col] = map[row][col];
                }
            }
            map = m;
        }

        private void clearVisited() {
            for(int r = 0; r < map.length; r++) {
                for(int c = 0; c < map[0].length; c++) {
                    if(map[r][c] == VISITED) map[r][c] = WATER;
                }
            }
        }

        private boolean bfs() {
            boolean[][] inQueue = new boolean[map.length][map[0].length];
            for(int r = 0; r < map.length; r++) {
                for(int c = 0; c < map[0].length; c++) {
                    inQueue[r][c] = false;
                }
            }

            Queue<Location> q = new LinkedList<>();
            q.offer(locOfSwans[0]);
            inQueue[locOfSwans[0].row][locOfSwans[0].col] = true;


            while(!q.isEmpty()) {
                Location l = q.poll();
                if(l.equals(locOfSwans[1])) {
                    clearVisited();
                    return true;
                }
                inQueue[l.row][l.col] = false;
                map[l.row][l.col] = VISITED;

                for(int i = 0; i < D_ROW.length; i++) {
                    int r = l.row + D_ROW[i];
                    int c = l.col + D_COL[i];

                    if(r >= 0 && r < map.length && c >= 0 && c < map[0].length && map[r][c] == WATER && !inQueue[r][c]) {
                        q.offer(new Location(r, c));
                        inQueue[r][c] = true;
                    }
                }
            }

            clearVisited();
            return false;
        }



        public boolean possibleToMeet() {
            return bfs();
        }
    }   // end of Lake



    private static char[][] map;

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            String[] ss = kb.readLine().split("\\s+");
            int row = Integer.parseInt(ss[0]);
            int col = Integer.parseInt(ss[1]);

            map = new char[row][col];

            for(int i = 0; i < row; i++) {
                String s = kb.readLine();
                int j = 0;
                for(char c : s.toCharArray()) {
                    map[i][j++] = c;
                }
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static int simulate() {
        Lake lake = new Lake(map);
        int elasped_day = 0;

        while(!lake.possibleToMeet()) {
            lake.aDayPasses();
            elasped_day++;
        }
        return elasped_day;
    }

    public static void main(String[] args) {
        if(input()) System.out.println(simulate());
    }
}
