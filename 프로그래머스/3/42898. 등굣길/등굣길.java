class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        dp[1][1] = 1;
        
        for(int[] point : puddles) {
            int x = point[0];
            int y = point[1];
            dp[y][x] = -1;
        }
        
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(dp[i][j]==-1 || i==1&&j==1)
                    continue;
                int a = dp[i-1][j] == -1 ? 0 : dp[i-1][j];
                int b = dp[i][j-1] == -1 ? 0 : dp[i][j-1];
                dp[i][j] = (a + b) % 1000000007;     
            }
        }
        
        return dp[n][m];
    }
}