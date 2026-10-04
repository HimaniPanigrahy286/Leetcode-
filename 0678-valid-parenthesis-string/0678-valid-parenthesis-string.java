class Solution {
    public boolean checkValidString(String s) {
        int l =0;
        int h =0;
        for(int i =0;i<s.length();i++)
        {
            if(s.charAt(i)=='(')
            {
                l++ ;
                h++ ;
            }
            else if(s.charAt(i)==')')
            {
                l-- ;
                h-- ;
            }
            else//if it is a *
            {
                l-- ;
                h++ ;
            }
            if(h<0)
            {
                return false ;
            }
            if(l<0) 
            l=0 ; 
        }
        return l==0 ;
    }
}