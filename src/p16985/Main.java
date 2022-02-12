package p16985;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int SIZE = 5;

    private static final int dx[] = { 0, 0, -1, 1, 0, 0 };
    private static final int dy[] = { -1, 1, 0, 0, 0, 0 };
    private static final int dz[] = { 0, 0, 0, 0, -1, 1 };

    private static class Location {
        public int x, y, z;

        public Location(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static byte[][] rotate(byte[][] arr) {
        byte[][] answer = new byte[SIZE][SIZE];

        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                answer[c][SIZE - r - 1] = arr[r][c];
            }
        }
        return answer;
    }

    private static void show(byte[][] arr) {
        for(byte[] a : arr) {
            for(byte t : a) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    private static byte[][][] cube = new byte[SIZE][SIZE][SIZE];

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            for(int i = 0; i < SIZE * SIZE; i++) {
                int z = i / SIZE;
                int y = i % SIZE;
                String[] ss = kb.readLine().split("\\s+");
                for(int x = 0; x < SIZE; x++) {
                    cube[z][y][x] = Byte.parseByte(ss[x]);
                }
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
    }
}
