package p16918;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static class Board {
        private static final byte EMPTY = -1;

        private static final int[] d_row = { -1, 1, 0, 0 };
        private static final int[] d_col = { 0, 0, -1, 1 };

        private byte[][] map;

        public Board(String[] arr) {
            map = new byte[arr.length][];
            int i = 0;
            for(String s : arr) {
                map[i] = new byte[s.length()];
                int j = 0;
                for(char c : s.toCharArray()) {
                    map[i][j] = (c == 'O') ? (byte)3 : EMPTY;
                    j++;
                }
                i++;
            }
        }

        public void pass() {
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[0].length; j++) {
                    if(map[i][j] > 0) map[i][j]--;
                }
            }
        }

        public void explode() {
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    if(map[i][j] == 0) {
                        for(int k = 0; k < d_row.length; k++) {
                            int r = i + d_row[k];
                            int c = j + d_col[k];

                            if(r >= 0 && r < map.length && c >= 0 && c < map[0].length && map[r][c] > 0) {
                                map[r][c] = -1;
                            }
                        }
                        map[i][j] = -1;
                    }
                }
            }
        }

        public void plantBombs() {
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    if(map[i][j] == EMPTY) map[i][j] = 3;
                }
            }
        }

        public void show() {
            for(int i = 0; i < map.length; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < map[i].length; j++) {
                    if(map[i][j] == EMPTY) sb.append('.');
                    else sb.append('O');
                }
                System.out.println(sb.toString());
            }
        }
    }

    private static String[] data;
    private static int r, c, n;

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            String s = kb.readLine();
            String[] ss = s.split("\\s+");
            r = Integer.parseInt(ss[0]);
            c = Integer.parseInt(ss[1]);
            n = Integer.parseInt(ss[2]);

            data = new String[r];
            for(int i = 0; i < r; i++) {
                data[i] = kb.readLine();
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static void printAnswer() {
        Board board = new Board(data);
        int t = 1;
        board.pass();
        if(n == t) {
            board.show();
            return;
        }

        while(true) {
            t++;
            board.pass();
            board.plantBombs();
            if(t == n) break;

            t++;
            board.pass();
            board.explode();
            if(t == n) break;
        }

        board.show();
    }

    public static void main(String[] args) {
        if(input()) {
            printAnswer();
        }
    }
}
