import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int n = 0; // 최대 알고력
        int m = 0; // 최대 코딩력
        
        for(int[] problem : problems) {
            n = Math.max(n, problem[0]);
            m = Math.max(m, problem[1]);
        }
        
        // 0 ≤ alp,cop ≤ 150
        // 0 ≤ alp_req,cop_req ≤ 150
        alp = Math.min(alp, n);
        cop = Math.min(cop, m);
        
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[i].length;j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp;i<=n;i++) {
            for(int j=cop;j<=m;j++) {  
                // 알고력 공부
                if(i+1<=n) 
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                // 코딩력 공부
                if(j+1<=m) 
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                // 문제 풀이
                for(int[] problem : problems) {
                    if(i>=problem[0] && j>=problem[1]) { // 필요 알고력, 코딩력을 충족했을 시
                        int ni = i + problem[2] > n ? n : i + problem[2];
                        int nj = j + problem[3] > m ? m : j + problem[3];
                        
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        
        
        return dp[n][m];
    }
}