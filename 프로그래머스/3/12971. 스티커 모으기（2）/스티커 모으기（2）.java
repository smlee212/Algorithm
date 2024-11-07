class Solution {
    
    public int solution(int sticker[]) {
        int n = sticker.length;
        int[] dp = new int[n+1];
        
        if(n == 1) {
            return sticker[0];
        }
        else if(n == 2) {
            return Math.max(sticker[0], sticker[1]);
        }
        
        dp[0] = sticker[0];
        dp[1] = sticker[0];
        for(int i=2;i<n-1;i++) {
            dp[i] = Math.max(dp[i-2] + sticker[i], dp[i-1]);
        }        
        int maxSum = dp[n-2];
        
        dp[0] = 0;
        dp[1] = sticker[1];
        for(int i=2;i<n;i++) {
            dp[i] = Math.max(dp[i-2] + sticker[i], dp[i-1]);
        }
        maxSum = Math.max(maxSum, dp[n-1]);
        
        return maxSum;
    }
}