
import java.util.*;

class Solution {

    static class Pair {
        int node, count, distance;

        Pair(int node, int count, int distance) {
            this.node = node;
            this.count = count;
            this.distance = distance;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        int[][] dist = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.distance, b.distance)
        );

        dist[0][1] = 0;
        pq.offer(new Pair(0, 1, 0));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();

            int u = curr.node;
            int count = curr.count;
            int d = curr.distance;

            if (d != dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return d;
            }

            for (int[] edge : adj[u]) {

                int v = edge[0];
                int w = edge[1];

                int newCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                if (newCount > k) {
                    continue;
                }

                int newDist = d + w;

                if (newDist < dist[v][newCount]) {

                    dist[v][newCount] = newDist;

                    pq.offer(new Pair(v, newCount, newDist));
                }
            }
        }

        return -1;
    }
}
