class Solution {
    public int minInsertions(String s) {
        Stack<Character> stack = new Stack<>();
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
            } 
            else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } 
                else {
                    ans++;
                }

                if (!stack.isEmpty()) {
                    stack.pop();
                } 
                else {
                    ans++;
                }
            }
        }

        ans += stack.size() * 2;
        return ans;
    }
}