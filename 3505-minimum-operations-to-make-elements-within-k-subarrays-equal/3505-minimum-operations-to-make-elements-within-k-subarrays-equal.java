
import java.util.*;

class Solution {

    class MyMap {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;
    }

    public long minOperations(int[] nums, int x, int k) {

        List<Long> cost = getMinOps(nums, x);

        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];

        for (long[] row : dp) {
            Arrays.fill(row, Long.MAX_VALUE / 2);
        }

        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {

                if (dp[i][j] == Long.MAX_VALUE / 2)
                    continue;

                // Skip current index
                dp[i + 1][j] = Math.min(
                    dp[i + 1][j], dp[i][j]
                );

                // Select window of size x
                if (j < k && i + x <= n) {
                    dp[i + x][j + 1] = Math.min(
                        dp[i + x][j + 1],
                        dp[i][j] + cost.get(i)
                    );
                }
            }
        }

        long ans = Long.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, dp[i][k]);
        }

        return ans;
    }

    private List<Long> getMinOps(int[] nums, int x) {

        List<Long> result = new ArrayList<>();

        MyMap lower = new MyMap();
        MyMap upper = new MyMap();

        for (int i = 0; i < nums.length; i++) {

            int val = nums[i];

            if (lower.map.isEmpty() || val <= lower.map.lastKey()) {
                lower.map.merge(val, 1, Integer::sum);
                lower.sum += val;
                lower.size++;
            } else {
                upper.map.merge(val, 1, Integer::sum);
                upper.sum += val;
                upper.size++;
            }

            if (i >= x) {

                int remove = nums[i - x];

                if (lower.map.containsKey(remove)) {
                    lower.map.merge(remove, -1, Integer::sum);

                    if (lower.map.get(remove) == 0)
                        lower.map.remove(remove);

                    lower.sum -= remove;
                    lower.size--;

                } else {
                    upper.map.merge(remove, -1, Integer::sum);

                    if (upper.map.get(remove) == 0)
                        upper.map.remove(remove);

                    upper.sum -= remove;
                    upper.size--;
                }
            }

            // Balance the two halves
            while (lower.size < upper.size) {
                int v = upper.map.firstKey();

                upper.map.merge(v, -1, Integer::sum);
                if (upper.map.get(v) == 0)
                    upper.map.remove(v);

                lower.map.merge(v, 1, Integer::sum);

                upper.sum -= v;
                lower.sum += v;

                upper.size--;
                lower.size++;
            }

            while (lower.size > upper.size + 1) {
                int v = lower.map.lastKey();

                lower.map.merge(v, -1, Integer::sum);
                if (lower.map.get(v) == 0)
                    lower.map.remove(v);

                upper.map.merge(v, 1, Integer::sum);

                lower.sum -= v;
                upper.sum += v;

                lower.size--;
                upper.size++;
            }

            // Calculate window cost
            if (i >= x - 1) {

                long median = lower.map.lastKey();

                long operations =
                    median * lower.size - lower.sum
                    + upper.sum - median * upper.size;

                result.add(operations);
            }
        }

        return result;
    }
}
