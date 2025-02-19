class Solution {
    public int solution(int n, int[] tops) {
        
        /* 아래 방향 삼각형 중 i번째 삼각형을 채우는 경우의 수
           1. 정삼각형
           2. 위 마름모
           3. 왼쪽 마름모
           4. 오른쪽 마름모
           
           dp[i][0] : 4번 방법
           dp[i][1] : 1, 2, 3번 방법
        */
        int[][] dp = new int[n+1][2];
        
        dp[0][0] = 0;
        dp[0][1] = 1;
        for(int i=1;i<=n;i++) {
            // 위 삼각형이 있을 경우
            if(tops[i-1] == 1) {
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 10007;
                dp[i][1] = (2 * dp[i-1][0] + 3 * dp[i-1][1]) % 10007;
            }
            // 없을 경우
            else {
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 10007;
                dp[i][1] = (dp[i-1][0] + 2 * dp[i-1][1]) % 10007;
            }
        }
        
        return (dp[n][0] + dp[n][1]) % 10007;
    }
}