class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long current = 0;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];

            long delta = queryAndAdd(1, 0, n - 1, prev + 1, i);

            current = (current + 2 * delta + i - prev) % MOD;
            ans = (ans + current) % MOD;

            last[nums[i]] = i;
        }

        return (int) ans;
    }

    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = (left + right) / 2;
        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }

    private void apply(int node, int left, int right, long val) {
        sum[node] = (sum[node] + (right - left + 1L) * val) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private long queryAndAdd(
        int node, int left, int right, int ql, int qr
    ) {
        if (ql > right || qr < left) {
            return 0;
        }

        if (ql <= left && right <= qr) {
            long result = sum[node];
            apply(node, left, right, 1);
            return result;
        }

        push(node, left, right);

        int mid = (left + right) / 2;

        long result = queryAndAdd(
            node * 2, left, mid, ql, qr
        );

        result = (result + queryAndAdd(
            node * 2 + 1, mid + 1, right, ql, qr
        )) % MOD;

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        return result;
    }
}