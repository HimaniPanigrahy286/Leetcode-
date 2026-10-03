class Solution {
    public String simplifyPath(String path) {
        Stack<String> st= new Stack<>() ;
        String[] parts = path.split("/") ;
        for(String p : parts)
        {
            if(p.equals(".")||p.equals(""))
            continue ;
            else 
            {   if(p.equals(".."))
                    {   
                        if(!st.isEmpty())
                            st.pop() ;
                    }
                else
                st.push(p) ;
            }
            
        }
        String ans="" ;
        for(String p :st)
        {
            ans = ans+"/"+p ;
        }
        if(ans.equals(""))
        return "/" ;


       return ans ;
    }
}