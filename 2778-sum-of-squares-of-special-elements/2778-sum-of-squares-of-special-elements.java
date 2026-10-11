class Solution {
    public int sumOfSquares(int[] nums) {
        int sum =0 ;
        int n =nums.length ;
        int i =1 ;
        while(i<=n)
        {
            if(n%i==0)
            sum+=(nums[i-1]*nums[i-1]);
            i++ ;
        }
        return sum ;
    }
}