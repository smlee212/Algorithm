class Solution {
    
    public int solution(int n, int[] money) {
        int answer = 0;
        
        long[] dp = new long[n+1];
        
        for(int i=0;i<money.length;i++) {
            int now = money[i];
            if(now > n) continue;
            dp[now]++;
            for(int j=1;j<=n;j++) {
                if(j >= now) {
                    dp[j] += dp[j-now];
                    dp[j] %= 1000000007;
                }
            }            
        }
        
        
        return (int) dp[n];
    }
}