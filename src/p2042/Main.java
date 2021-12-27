package p2042;

public class Main {
    private static class SegmentTree {
        private long[] tree;    //complete binary tree
        private int size;       //size of array of tree
        private int sizeOfNodesInLastLevelOfComplete;
        private int sizeOfNodesInLastLevelOfFull;     // full binary tree

        private long[] arr; // tempary used
        
        public SegmentTree(long[] arr) {
            int level = (int)Math.log(arr.length);
            int sizeOfNodes = (int)Math.pow(2, level);

            // if full binary tree
            if(sizeOfNodes == arr.length) {
                tree = new long[sizeOfNodes * 2 - 1];
                sizeOfNodesInLastLevelOfFull = sizeOfNodes;
            } else {
                tree = new long[sizeOfNodes * 2 - 1 + arr.length];
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
            if(index >= sizeOfNodesInLastLevelOfComplete) return;

            if(left == right) {
                tree[node] += diff;
                return;
            } else {
                if(index >= left && index <= right) {
                    tree[node] += diff;
                    int half = (left + right) / 2;
                    update(node * 2 + 1, left, half, index, diff);
                    update(2 * (node + 1), half + 1, right, index, diff);
                }
            }
        }

        private long sum(int node, int left, int right, int start, int end) {

        }

        public void show() {
            for(long t : tree) {
                System.out.print(t + " ");
            }
        }
    }

    public static void main(String[] args) {
        long[] arr = { 1, 2, 3, 4 };

        SegmentTree tree = new SegmentTree(arr) ;
        tree.show();
    }

}
