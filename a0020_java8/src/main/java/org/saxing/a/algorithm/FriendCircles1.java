package org.saxing.a.algorithm;

/**
 * leet code 547
 */
public class FriendCircles1 {

    public static void main(String[] args) {
        int[][] M = new int[][]{{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
//        int[][] M = new int[][] {{1,1,0}, {1,1,0}, {0,0,1}};

        int circleNum = new FriendCircles1().findCircleNum(M);
        System.out.println(circleNum);
    }

    class UnionFind547_2 {
        private int count = 0;
        private int[] parent, rank;

        public UnionFind547_2(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            }
            else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }

        public int count() {
            return count;
        }
    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind547_2 uf = new UnionFind547_2(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1)
                    uf.union(i, j);
            }
        }
        return uf.count();
    }
}
