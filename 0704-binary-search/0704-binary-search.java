class Solution {
    public int search(int[] nums, int target) {
        int l=0;
        int r=nums.length-1 ;
        while(l<=r)
        {
            int mid=r+(l-r)/2 ;
            if(nums[mid]==target)
            return mid ;
            else if(target>=nums[0]&&target<nums[mid])
            r=mid-1 ;
            else
            l=mid+1 ;
        }
        return -1 ;
    }
}