class Solution {
    Set<String> set = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        
        int left = 0, right = 0;

        for(char c : s.toCharArray()) {
            if(c == '(')
                left++;
            else if(c == ')') {
                if(left > 0)
                    left--;
                else
                    right++;
            }
        }

        solve(s, 0, left, right, 0, "");

        return new ArrayList<>(set);
    }

    void solve(String s, int i, int l, int r, int bal, String str) {

        if(i == s.length()) {
            if(l == 0 && r == 0 && bal == 0)
                set.add(str);
            return;
        }

        char c = s.charAt(i);

        if(c == '(' && l > 0)
            solve(s, i + 1, l - 1, r, bal, str);

        if(c == ')' && r > 0)
            solve(s, i + 1, l, r - 1, bal, str);

        if(c == '(')
            solve(s, i + 1, l, r, bal + 1, str + c);

        else if(c == ')' && bal > 0)
            solve(s, i + 1, l, r, bal - 1, str + c);

        else if(c != '(' && c != ')')
            solve(s, i + 1, l, r, bal, str + c);
    }
}