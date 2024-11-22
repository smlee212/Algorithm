class Solution {
    
    int maxLen = 0;
    
    public int solution(String s) {
        char[] c = s.toCharArray();
        
        if(c.length == 1) {
            return 1;
        }
        
        for(int i=1;i<c.length;i++) {
            find(c, i, i);
            find(c, i-1, i);
        }
        
        return maxLen;
    }
    
    void find(char[] c, int left, int right) {
        while(isValid(c, left, right)) {
            if(c[left] == c[right]) {
                left--;
                right++;
            }
            else break;
        }
        maxLen = Math.max(maxLen, right - left - 1);
    }
    
    boolean isValid(char[] c, int left, int right) {
        return left >= 0 && right < c.length;
    }
}