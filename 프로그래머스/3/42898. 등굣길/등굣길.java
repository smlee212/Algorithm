import java.util.*;

class Solution {

    // 10
    public int solution(int m, int n, int[][] puddles) {
        final int MOD = 1000000007;
        
        int[][] dp = new int[m+1][n+1];
        for(int[] p : puddles) {
            dp[p[0]][p[1]] = -1;
        }
        
        dp[0][1] = 1;
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(dp[i][j] == -1) continue;
                int left = dp[i-1][j] == -1 ? 0 : dp[i-1][j];
                int up = dp[i][j-1] == -1 ? 0 : dp[i][j-1];
                dp[i][j] = (left + up) % MOD;
            }
        }
        
        return dp[m][n];
    }
}