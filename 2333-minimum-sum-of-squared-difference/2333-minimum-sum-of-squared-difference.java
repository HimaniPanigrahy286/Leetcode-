class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long sum = 0;
        int max = 0;
        long k = (long) k1 + k2;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
            max = Math.max(max, diff[i]);
        }

        if (sum <= k) return 0;

        int low = 0, high = max;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long need = 0;

            for (int d : diff) {
                need += Math.max(0, d - mid);
            }

            if (need <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            k -= Math.max(0, diff[i] - low);
            diff[i] = Math.min(diff[i], low);
        }

        for (int i = 0; i < n && k > 0; i++) {
            if (diff[i] == low) {
                diff[i]--;
                k--;
            }
        }

        long ans = 0;

        for (int d : diff) {
            ans += (long) d * d;
        }

        return ans;
    }
}