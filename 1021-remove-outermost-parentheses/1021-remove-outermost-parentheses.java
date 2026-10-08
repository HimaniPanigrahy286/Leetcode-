class Solution {
    public String removeOuterParentheses(String s) {
        Stack <Integer> st = new Stack<>();
        String str ="" ;
        int l =0 ;
        while(l<s.length())
        {
            if(s.charAt(l)=='(')
            {
                if(!st.isEmpty())
                {     st.push(l) ;
                    str = str+"(" ;
                }
                else
                {
                     st.push(l) ;
                }
                
            }
            else
            {
                int x =st.pop() ;
                if(!st.isEmpty())
                {
                    str=str+")" ;
                }

            }
            l++ ;
        }
        return str ;
    }
}