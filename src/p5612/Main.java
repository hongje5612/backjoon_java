package p5612;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static class Record {
        public int in, out;

        public Record(int in, int out) {
            this.in = in;
            this.out = out;
        }
    }

    private static List<Record> data;
    private static int m;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            data = new ArrayList<>();

            int n = kb.nextInt();
            m = kb.nextInt();

            for(int i = 0; i < n; i++) {
                int in = kb.nextInt();
                int out = kb.nextInt();

                data.add(new Record(in, out));
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    public static void main(String[] args) {
        if(input()) {
            int max = m;

            for(Record r : data) {
                m += r.in;
                m -= r.out;

                if(m < 0) {
                    max = 0;
                    break;
                }

                if(m > max) max = m;
            }
            System.out.println(max);
        }
    }
}
