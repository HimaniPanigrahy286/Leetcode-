class Solution {
    public int balancedStringSplit(String s) {
       
       int i =0;
       int c =0 ;
       int rCount =0;
       int lCount =0 ;
    //    int max=Integer.MIN_VALUE ; 
       while(i<s.length())
       {
        if(s.charAt(i)=='R')
        rCount++ ;
       else{
        lCount++ ;
       }
        if(rCount==lCount)
        {
            c++ ;
            i++ ;
        }
        else{
            i++ ;
        }
       } 
       return c ;
    }
}