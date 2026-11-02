import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int median = nums[n / 2];

        int count = 0;

        for (int i = 0; i < n; i++) {
            count += Math.abs(nums[i] - median);
        }

        return count;
    }
}