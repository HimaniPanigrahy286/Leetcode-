class Solution {
    public List<String> generateParenthesis(int n) {
         List<String> ans = new ArrayList<>();

        backtrack(ans, "", 0, 0, n);

        return ans;
        }

        public void backtrack(List<String> ans, String s,
                          int open, int close, int n) {

        if (open == n && close == n) {
            ans.add(s);
            return;
        }

        if (open < n) {
            backtrack(ans, s + "(", open + 1, close, n);
        }

        if (close < open) {
            backtrack(ans, s + ")", open, close + 1, n);
        }
    }
}