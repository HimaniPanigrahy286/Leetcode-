class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < n; i++) {
            int right = (zero + i) % n;
            int prevRight = (zero + i - 1) % n;

            if (nums[right] < nums[prevRight]) {
                increasing = false;
            }

            int left = (zero - i + n) % n;
            int prevLeft = (zero - i + 1 + n) % n;

            if (nums[left] < nums[prevLeft]) {
                decreasing = false;
            }
        }

        if (increasing) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }

        if (decreasing) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}