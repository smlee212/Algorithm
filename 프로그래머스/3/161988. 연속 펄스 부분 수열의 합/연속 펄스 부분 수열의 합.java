import java.util.*;

class Solution {
    
    /*
        1. 펄스를 곱한 두 결과는 부호만 다른 결과값이 나옴 
        2. 부분 수열에 펄스를 곱하는 것이 아니라 기존 수열에 미리 펄스를 곱해놓으면 더 효율적임
        3. n^2 은 시간초과 / nlogn 도 간당간당..?
        4. dp는 어떨까?
    */
    
    public long solution(int[] sequence) {        
        // dp[i][0] : i번째 값을 + 했을 때 최대값
        // dp[i][1] : i번째 값을 - 했을 때 최대값
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