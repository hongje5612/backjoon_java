package p1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static class Pair {
        public int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static int n, m;
    private static List<Pair> list;

    private static void printAnswer() {
        List<Integer> l = new ArrayList<>();

        for(var p : list) {
            l.add(p.first);
            l.add(p.second);
        }

        l.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        var it = l.iterator();
        Integer t;
        if(it.hasNext()) t = it.next();
        else t = null;

        List<Pair> pairList = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            if(t != null) {
                if(t == i) continue;
                else pairList.add(new Pair(i, i));
            }
        }

        pairList.addAll(list);

        pairList.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b) {
                return Integer.compare(a.first, b.second);
            }
        });

        for(var p : pairList) {
            if(p.first == p.second) System.out.print(p.first + " ");
            else System.out.print(p.first + " " + p.second + " ");
        }
    }

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            String[] ss = kb.readLine().split("\\s+");
            n = Integer.parseInt(ss[0]);
            m = Integer.parseInt(ss[1]);
            list = new ArrayList<>();

            for(int i = 0; i < m; i++) {
                ss = kb.readLine().split("\\s+");
                int first = Integer.parseInt(ss[0]);
                int second = Integer.parseInt(ss[1]);
                list.add(new Pair(first, second));
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
        if(input()) printAnswer();
    }
}
