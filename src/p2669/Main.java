package p2669;

import java.util.Scanner;

public class Main {
    private static class Rect {
        public byte x1, y1, x2, y2;

        public Rect(byte x1, byte y1, byte x2, byte y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private static final int MAX = 100;
    private static final int SIZE = 4;
    private static Rect[] rects = new Rect[SIZE];

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            for(int i = 0; i < SIZE; i++) {
                byte x1 = kb.nextByte();
                byte y1 = kb.nextByte();
                byte x2 = kb.nextByte();
                byte y2 = kb.nextByte();

                rects[i] = new Rect(x1, y1, x2, y2);
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static int solve() {
        byte[][] arr = new byte[MAX + 1][MAX + 1];

        for(int i = 0; i < SIZE; i++) {
            Rect r = rects[i];
            for(int x = r.x1; x < r.x2; x++) {
                for(int y = r.y1; y < r.y2; y++) {
                    arr[y][x] = 1;
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < MAX + 1; i++) {
            for(int j = 0; j < MAX + 1; j++) {
                if(arr[i][j] == 1) answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        if(input()) {
            System.out.println(solve());
        }
    }
}
