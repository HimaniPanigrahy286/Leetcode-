class Solution {
    public int mostFrequent(int[] nums, int key) {
       HashMap<Integer,Integer> map = new HashMap<>() ;
       int maxFreq= Integer.MIN_VALUE ;
       int ans=0  ;
       int target ;
       for(int i =0;i<nums.length-1;i++)
       {
        if(nums[i]==key)
        {
            target= nums[i+1] ;
            map.put(target,map.getOrDefault(target,0)+1) ;
            if(map.get(target)>maxFreq)
            {
                maxFreq=map.get(target) ;
                ans = target ;
            }
        }
       }
       return ans ;
    }
}