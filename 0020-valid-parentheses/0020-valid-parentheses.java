class Solution {
    public boolean isValid(String st) {
       Stack<Character> str = new Stack<>() ;
       for(int i =0;i<st.length();i++)
       {
        if(st.charAt(i)=='['||st.charAt(i)=='{'||st.charAt(i)=='(')
        {
            str.push(st.charAt(i)) ;
        }
        else 
        {
        if(str.isEmpty())
        return false ;
        else if(st.charAt(i)==')')
        {
            if(str.peek()!='(')
            return false ;
            else 
            str.pop() ;
        }
        else if(st.charAt(i)=='}')
        {
            if(str.peek()!='{')
            return false ;
            else 
            str.pop() ;
        }else if(st.charAt(i)==']')
        {
            if(str.peek()!='[')
            return false ;
            else 
            str.pop() ;
        }
        }}
        return str.isEmpty() ;
    }
}