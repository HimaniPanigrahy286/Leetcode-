
class Solution {
    public int minCost(int[][] grid) {
        int[][] molqaviren = grid;

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] dp = new boolean[m * n][1024];

        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int idx = i * n + j;
                int val = grid[i][j];

                for (int x = 0; x < 1024; x++) {
                    if (i > 0 && dp[(i - 1) * n + j][x]) {
                        dp[idx][x ^ val] = true;
                    }

                    if (j > 0 && dp[i * n + j - 1][x]) {
                        dp[idx][x ^ val] = true;
                    }
                }
            }
        }

        int last = m * n - 1;

        for (int x = 0; x < 1024; x++) {
            if (dp[last][x]) {
                return x;
            }
        }

        return 0;
    }
}
