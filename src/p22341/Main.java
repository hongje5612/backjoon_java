package p22341;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/22341
 */
public class Main {
    private static class Point {
        public short x, y;

        public Point(short x, short y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Paper {
        private short x, y;

        public Paper(short x, short y) {
            this.x = x;
            this.y = y;
        }

        public boolean isInside(Point point) {
            return point.x <= x && point.y <= y;
        }

        public int getAreaWhenCutVertically(Point point) {
            return point.x * y;
        }

        public int getAreaWhenCutHorizontally(Point point) {
            return x * point.y;
        }

        public void cutVertically(Point point) {
            x = point.x;
        }

        public void cutHorizontally(Point point) {
            y = point.y;
        }

        public int getArea() {
            return x * y;
        }
    }

    private static List<Point> points;
    private static short n;

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            String[] ss = kb.readLine().split("\\s+");
            n = Short.parseShort(ss[0]);
            int c = Short.parseShort(ss[1]);

            points = new ArrayList<>();

            for(int i = 0; i < c; i++) {
                ss = kb.readLine().split("\\s+");
                points.add(new Point(Short.parseShort(ss[0]), Short.parseShort(ss[1])));
            }
            state = true;
        } catch (IOException e) {
            state = false;
        } catch (Exception e) {
            state = false;
        }

        return state;
    }

    private static void printAnswer() {
        Paper paper = new Paper(n, n);

        for(Point p : points) {
            if(paper.isInside(p)) {
                int area1 = paper.getAreaWhenCutHorizontally(p);
                int area2 = paper.getAreaWhenCutVertically(p);

                if(area2 >= area1) {
                    paper.cutVertically(p);
                } else {
                    paper.cutHorizontally(p);
                }
            }
        }

        System.out.println(paper.getArea());
    }

    public static void main(String[] args) {
        if(input()) printAnswer();
    }
}
