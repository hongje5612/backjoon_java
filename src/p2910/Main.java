package p2910;

import java.util.*;

public class Main {
    private static List<Integer> data;

    private static boolean input() {
        boolean state;

        try(Scanner kb = new Scanner(System.in)) {
            data = new ArrayList<>();

            int n = kb.nextInt();
            int c = kb.nextInt();

            for(int i = 0; i < n; i++) {
                int t = kb.nextInt();
                data.add(t);
            }
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    private static class Number {
        public int number;
        public int order;
        public int frequent;
    }

    private static void printAnswer() {
        Map<Integer, Integer> frequent = new HashMap<>();
        Map<Integer, Integer> order = new HashMap<>();

        int o = 0;
        for(int t : data) {
            if(frequent.containsKey(t)) frequent.replace(t, frequent.get(t) + 1);
            else frequent.put(t, 1);

            if(!order.containsKey(t)) order.put(t, o);
            o++;
        }

        List<Number> l = new ArrayList<>();

        for(int t : data) {
            Number n = new Number();
            n.number = t;
            n.order = order.get(t);
            n.frequent = frequent.get(t);
            l.add(n);
        }

        l.sort(new Comparator<Number>() {
            @Override
            public int compare(Number a, Number b) {
                int t = Integer.compare(a.frequent, b.frequent);
                if(t == -1) return 1;
                else if(t == 1) return -1;
                else {
                    return Integer.compare(a.order, b.order);
                }
            }
        });

        for(Number n : l) {
            System.out.print(n.number + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        if(input()) printAnswer();
    }
}
