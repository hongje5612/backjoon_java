package p1997;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static class Edge {
        public short v1, v2;
        public int weight;

        public Edge(short v1, short v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

    private static class DisjointSet {
        private short[] parents;
        private int[] size;

        public DisjointSet(short size) {
            int s = size + 1;
            parents = new short[s];
            this.size = new int[s];
            for(short i = 0; i < s; i++) {
                parents[i] = i;
                this.size[i] = 1;
            }
        }

        public void weightedUnion(short v1, short v2) {
            short pv1 = findSet(v1);
            short pv2 = findSet(v2);

            if(size[pv1] >= size[pv2]) {
                parents[pv2] = pv1;
                size[pv1] += size[pv2];
            }
            else {
                parents[pv1] = pv2;
                size[pv2] += size[pv2];
            }
        }

        public short findSet(short vertex) {
            short v = vertex;

            while(vertex != parents[vertex]) vertex = parents[vertex];

            //path compression
            while(v != vertex) {
                short t = parents[v];
                parents[v] = vertex;
                v = t;
            }
            return vertex;
        }
    }

    private static short v; // 정점의 개수
    private static int e; // 에지의 개수
    private static List<Edge> edges;

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            String buf = kb.readLine();
            String[] ss = buf.split("\\s+");
            v = Short.parseShort(ss[0]);
            e = Integer.parseInt(ss[1]);

            edges = new ArrayList<>();

            for(int i = 0; i < e; i++) {
                buf = kb.readLine();
                ss = buf.split("\\s+");
                short v1 = Short.parseShort(ss[0]);
                short v2 = Short.parseShort(ss[1]);
                int weight = Integer.parseInt(ss[2]);

                edges.add(new Edge(v1, v2, weight));
            }
            state = true;
        } catch (IOException e) {
            state = false;
        }
        return state;
    }

    private static int solve() {
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge a, Edge b) {
                return Integer.compare(a.weight, b.weight);
            }
        });

        DisjointSet set = new DisjointSet(v);
        int answer = 0;

        for(Edge edge : edges) {
            if(set.findSet(edge.v1) != set.findSet(edge.v2)) {
                answer += edge.weight;
                set.weightedUnion(edge.v1, edge.v2);
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
