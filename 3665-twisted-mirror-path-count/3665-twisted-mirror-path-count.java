import java.util.Arrays;

public class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][][] memo;
    private int m, n;

    // Renamed to match the LeetCode driver requirement
    public int uniquePaths(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        
        // memo[i][j][dir]: dir=0 means arrived via Right, dir=1 means arrived via Down
        this.memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        int totalPaths = 0;
        
        // Try moving Right initially from (0,0)
        int[] nextRight = getDestination(grid, 0, 1, 0);
        if (nextRight != null) {
            totalPaths = (totalPaths + dfs(grid, nextRight[0], nextRight[1], nextRight[2])) % MOD;
        }
        
        // Try moving Down initially from (0,0)
        int[] nextDown = getDestination(grid, 1, 0, 1);
        if (nextDown != null) {
            totalPaths = (totalPaths + dfs(grid, nextDown[0], nextDown[1], nextDown[2])) % MOD;
        }

        return totalPaths;
    }

    private int dfs(int[][] grid, int i, int j, int dir) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (memo[i][j][dir] != -1) {
            return memo[i][j][dir];
        }

        int paths = 0;

        // Option 1: Attempt to move Right
        int[] nextRight = getDestination(grid, i, j + 1, 0);
        if (nextRight != null) {
            paths = (paths + dfs(grid, nextRight[0], nextRight[1], nextRight[2])) % MOD;
        }

        // Option 2: Attempt to move Down
        int[] nextDown = getDestination(grid, i + 1, j, 1);
        if (nextDown != null) {
            paths = (paths + dfs(grid, nextDown[0], nextDown[1], nextDown[2])) % MOD;
        }

        return memo[i][j][dir] = paths;
    }

    private int[] getDestination(int[][] grid, int r, int c, int incomingDir) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return null; 
        }
        
        if (grid[r][c] == 0) {
            return new int[]{r, c, incomingDir};
        }
        
        if (incomingDir == 0) {
            return getDestination(grid, r + 1, c, 1);
        } else {
            return getDestination(grid, r, c + 1, 0);
        }
    }
}
