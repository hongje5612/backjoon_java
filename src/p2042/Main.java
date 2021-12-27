package p2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * 밑 수가 2 인 log 값
     * @param n
     * @return
     * 밑 수가 2 인 log 값, 소수점 밑으로 버린 값을 반환합니다.
     */
    private static int log2(int n) {
        if(n == 1) return 0;

        int x = 2;

        for(int answer = 1; true; answer++) {
            int x2 = x * 2;

            if(n >= x && n < x2) return answer;

            x = x2;
        }
    }

    private static class SegmentTree {
        private long[] tree;    //complete binary tree
        private int size;       //size of array of tree
        private int sizeOfNodesInLastLevelOfComplete;
        private int sizeOfNodesInLastLevelOfFull;     // full binary tree

        private long[] arr; // tempary used

        public SegmentTree(long[] arr) {
            int level = log2(arr.length);
            int sizeOfNodes = (int)Math.pow(2, level);

            // if full binary tree
            if(sizeOfNodes == arr.length) {
                size = sizeOfNodes * 2 - 1;
                tree = new long[size];
                sizeOfNodesInLastLevelOfFull = sizeOfNodes;
            } else {
                size = sizeOfNodes * 2 - 1 + arr.length;
                tree = new long[size];
                sizeOfNodesInLastLevelOfFull = sizeOfNodes * 2;
            }

            sizeOfNodesInLastLevelOfComplete = arr.length;
            this.arr = arr;

            init(0, 0, sizeOfNodesInLastLevelOfFull - 1);
        }

        private long init(int node, int left, int right) {
            if(left == right) {
                if (left < arr.length) return tree[node] = arr[left];
                else return 0;
            } else {
                int half = (left + right) / 2;
                return tree[node] = init(node * 2 + 1, left, half) + init(2 * (node + 1), half + 1, right);
            }
        }

        private void update(int node, int left, int right, int index, long diff) {
            if(index < left || index > right) return;

            tree[node] += diff;

            if(left != right) {
                int half = (left + right) / 2;
                update(node * 2 + 1, left, half, index, diff);
                update(2 * (node + 1), half + 1, right, index, diff);
            }
        }

        public void update(int index, long value) {
            long diff = value - arr[index];
            update(0, 0, sizeOfNodesInLastLevelOfFull - 1, index, diff);
        }

        private long sum(int node, int left, int right, int start, int end) {
            if(left > end || right < start) return 0;
            if(left >= start && right <= end) return tree[node];
            else {
                int half = (left + right) / 2;
                long a = sum(node * 2 + 1, left, half, start, end);
                long b = sum(2 * (node + 1), half + 1, right, start, end);
                return Math.addExact(a, b);
            }
        }

        public long sum(int start, int end) {
            return sum(0, 0, sizeOfNodesInLastLevelOfFull - 1, start, end);
        }

        public void show() {
            System.out.println(size);
            for(long t : tree) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    public static class Command {
        public byte a;
        public int b;
        public long c;

        public Command(byte a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    private static long[] arr;
    private static List<Command> commandList;

    private static boolean input() {
        boolean state;

        try(BufferedReader kb = new BufferedReader(new InputStreamReader(System.in))) {
            String[] ss = kb.readLine().split("\\s+");

            int n = Integer.parseInt(ss[0]);
            int m = Integer.parseInt(ss[1]);
            int k = Integer.parseInt(ss[2]);

            arr = new long[n];
            commandList = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(kb.readLine());
            }

            for(int i = 0; i < m + k; i++) {
                ss = kb.readLine().split("\\s+");

                commandList.add(new Command(Byte.parseByte(ss[0]), Integer.parseInt(ss[1]), Long.parseLong(ss[2])));
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
        if(input()) {
            SegmentTree tree = new SegmentTree(arr);

            for(Command command : commandList) {
                switch(command.a) {
                    case 1 :
                        tree.update(--command.b, command.c);
                        break;
                    case 2 :
                        System.out.println(tree.sum(--command.b, (int)--command.c));
                        break;
                }
            }
        }
    }
}
