package p3197;

import java.util.Scanner;

public class Main {

    private static class Location {
        public int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Lake {
        private static final char SWAN = 'L';
        private static final char WATER = '.';
        private static final char ICE = 'X';
        private static final int NUMBER_OF_SWANS = 2;

        private char[][] map;
        private Location[] locOfSwans;

        public Lake(char[][] map) {
            this.map = map;
            locOfSwans = new Location[NUMBER_OF_SWANS];
        }

        private static final int[] D_ROW = { -1, 1, 0, 0 };  //상하좌우
        private static final int[] D_COL = { 0, 0, -1, 1 };  //상하좌우

        private boolean inContackWithWater(int row, int col) {
            for(int i = 0; i < D_ROW.length; i++) {
                int r = row + D_ROW[i];
                int c = col + D_COL[i];

                if(r >= 0 && r < map.length && c >= 0 && c < map[0].length) {
                    if(map[r][c] == WATER || map[r][c] == SWAN) return true;
                }
            }
            return false;
        }

        public Lake aDayPasses() {
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
            return new Lake(m);
        }

        public void findOutLocationOfSwans() {
            int index = 0;
            for(int r = 0; r < map.length; r++) {
                for(int c = 0; c < map[0].length; c++) {
                    if(map[r][c] == SWAN) locOfSwans[index++] = new Location(r, c);
                }
            }
            show();
        }

        public void show() {
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[0].length; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }

        private static final char VISITED = 'V';
        private boolean contact;

        private void dfs(int row, int col) {
            if(contact) return;

            if(row == locOfSwans[1].row && col == locOfSwans[1].col) {
                contact = true;
                return;
            }

            char save = map[row][col];

            map[row][col] = VISITED;

            for(int i = 0; i < D_ROW.length; i++) {
                int r = row + D_ROW[i];
                int c = col + D_COL[i];

                if (r >= 0 && r < map.length && c >= 0 && c < map[0].length && map[r][c] != VISITED && map[r][c] != ICE) {
                    dfs(r, c);
                    map[r][c] = save;
                }
            }
        }

        public boolean possibleToMeet() {
            findOutLocationOfSwans();
            contact = false;
            dfs(locOfSwans[0].row, locOfSwans[0].col);
            return contact;
        }
    }   // end of Lake



    private static char[][] map;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            int row = kb.nextInt();
            int col = kb.nextInt();

            map = new char[row][col];

            for(int i = 0; i < row; i++) {
                String s = kb.next();
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
            lake = lake.aDayPasses();
            elasped_day++;
        }
        return elasped_day;
    }

    public static void main(String[] args) {
        if(input()) System.out.println(simulate());
    }
}
