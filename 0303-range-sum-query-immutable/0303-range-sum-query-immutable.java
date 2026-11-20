class NumArray {
      int []pref ;
    public NumArray(int[] nums) {
        pref=nums ;
        for(int i =1 ;i<nums.length;i++)
        {
            pref[i]+=pref[i-1] ;
        }
    }
    
    public int sumRange(int left, int right) {
        if(left ==0)
        return pref[right] ;

        return pref[right]-pref[left-1] ;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */