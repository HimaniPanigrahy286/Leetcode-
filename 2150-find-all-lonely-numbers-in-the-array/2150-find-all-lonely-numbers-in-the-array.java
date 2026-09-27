class Solution {
    public List<Integer> findLonely(int[] nums) {
    List<Integer> ans = new ArrayList<>() ;
    HashMap<Integer,Integer> map= new HashMap<>() ;
    for(int i =0;i<nums.length;i++)
    {
        map.put(nums[i],map.getOrDefault(nums[i],0)+1) ;
    }
    for(int val:map.keySet())
    {
        if(map.get(val)==1 && !map.containsKey(val+1) && !map.containsKey(val-1))
        {
            ans.add(val) ;
        }
    }
       
       return ans ;
    }
}