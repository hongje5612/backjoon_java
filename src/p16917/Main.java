package p16917;

import java.util.Scanner;

public class Main {
    private static short a, b, c;
    private static int x, y;

    private static boolean input() {
        boolean state;
        try(Scanner kb = new Scanner(System.in)) {
            a = kb.nextShort();
            b = kb.nextShort();
            c = kb.nextShort();
            x = kb.nextInt();
            y = kb.nextInt();

            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static long f(int x, int y)  {
        if(x == 0 && y == 0) return 0;
        else if(x == 0 && y > 0) {
            long s = (long)b * y;
            long t = (long)2 * c * y;
            return Math.min(s, t);
        }
        else if(x > 0 && y == 0) {
            long s = (long)a * x;
            long t = (long)2 * c * x;
            return Math.min(s, t);
        }
        else {
            if(a + b > 2 * c) {
                if(x < y) return f(0, y - x) + 2 * c * (long)x;
                else return f(x - y, 0) + 2 * c * (long)y;
            } else {
                return (long)x * a + (long)y * b;
            }
        }
    }

    public static void main(String[] args) {
        if(input()) {
            System.out.println(f(x, y));
        }
    }
}
