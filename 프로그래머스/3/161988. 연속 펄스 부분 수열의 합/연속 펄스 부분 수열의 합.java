import java.util.*;

class Solution {
    
    public long solution(int[] sequence) {        
        // dp[i][0] : i번째 값을 + 했을 때 (이전 연속 수열에 더하거나, i번째부터 시작하거나)
        // dp[i][1] : i번째 값을 - 했을 때 (이전 연속 수열에 빼거나, i번째부터 시작하거나)
        long[][] dp = new long[sequence.length][2];      
        dp[0][0] = sequence[0];
        dp[0][1] = -sequence[0];        
        
        long max = Math.max(dp[0][0], dp[0][1]);
        
        for(int i=1;i<sequence.length;i++) {            
            dp[i][0] = Math.max(dp[i-1][1], 0) + sequence[i]; 
            dp[i][1] = Math.max(dp[i-1][0], 0) - sequence[i];
            
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        
        return max;
    }
}