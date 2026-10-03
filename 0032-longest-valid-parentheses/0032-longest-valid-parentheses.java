class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>() ;
        int l =0;
        st.push(-1) ;
        int maxLength = 0;
        while(l<s.length())
        {   
            if(s.charAt(l)=='(')
            {
                st.push(l) ;
            }
            else
            {  st.pop() ;
            if(st.isEmpty())
            {
              st.push(l) ;              
            }
            else
            {
                maxLength=Math.max(maxLength,l-st.peek()) ;
            }}
            l++ ;  
        }
      return maxLength ;
    }
}