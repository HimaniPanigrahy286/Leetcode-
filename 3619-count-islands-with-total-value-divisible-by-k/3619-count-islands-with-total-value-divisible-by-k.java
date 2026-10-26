import java.util.*;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        int[] dirs = {-1, 0, 1, 0, -1};
        Stack<int[]> st = new Stack<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) {
                    continue;
                }

                long sum = 0;
                st.push(new int[]{i, j});

                sum += grid[i][j];
                grid[i][j] = 0;

                while (!st.isEmpty()) {
                    int[] cell = st.pop();
                    int r = cell[0];
                    int c = cell[1];

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dirs[d];
                        int nc = c + dirs[d + 1];

                        if (nr >= 0 && nr < m &&
                            nc >= 0 && nc < n &&
                            grid[nr][nc] > 0) {

                            sum += grid[nr][nc];
                            grid[nr][nc] = 0;

                            st.push(new int[]{nr, nc});
                        }
                    }
                }

                if (sum % k == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}