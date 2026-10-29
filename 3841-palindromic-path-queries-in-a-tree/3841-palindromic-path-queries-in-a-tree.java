
import java.util.*;

class Solution {
    private List<Integer>[] graph;
    private int[][] up;
    private int[] tin, tout, depth, mask;
    private int timer, LOG;
    private BIT bit;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        mask = new int[n];

        // Iterative DFS avoids stack overflow on a deep tree.
        int[] parent = new int[n];
        int[] order = new int[n];
        int[] stack = new int[n];
        Arrays.fill(parent, -1);

        int top = 0, count = 0;
        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];
            tin[u] = count;
            order[count++] = u;

            for (int v : graph[u]) {
                if (parent[v] != -1) continue;
                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int v = 0; v < n; v++) up[0][v] = parent[v];

        for (int k = 1; k < LOG; k++) {
            for (int v = 0; v < n; v++) {
                up[k][v] = up[k - 1][up[k - 1][v]];
            }
        }

        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            size[parent[order[i]]] += size[order[i]];
        }

        for (int v = 0; v < n; v++) {
            tout[v] = tin[v] + size[v] - 1;
        }

        char[] chars = s.toCharArray();
        bit = new BIT(n + 1);

        for (int v = 0; v < n; v++) {
            mask[v] = 1 << (chars[v] - 'a');
            bit.update(tin[v], mask[v]);
            bit.update(tout[v] + 1, mask[v]);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);
                int newMask = 1 << (c - 'a');

                if (chars[u] != c) {
                    int change = mask[u] ^ newMask;
                    bit.update(tin[u], change);
                    bit.update(tout[u] + 1, change);
                    chars[u] = c;
                    mask[u] = newMask;
                }
            } else {
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);

                int pathMask = bit.query(tin[u])
                             ^ bit.query(tin[v])
                             ^ mask[l];

                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    private static class BIT {
        int[] tree;
        int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            for (int i = index + 1; i <= n; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int query(int index) {
            int result = 0;
            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }
            return result;
        }
    }
}
