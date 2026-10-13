import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Step 1: Compute the square of each element
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort the squares in ascending order
        Arrays.sort(squares);
        
        // Step 3: Subtract the smallest n/2 elements, add the rest
        int minusCount = n / 2;
        long score = 0;
        
        for (int i = 0; i < minusCount; i++) {
            score -= squares[i];
        }
        for (int i = minusCount; i < n; i++) {
            score += squares[i];
        }
        
        return score;
    }
}