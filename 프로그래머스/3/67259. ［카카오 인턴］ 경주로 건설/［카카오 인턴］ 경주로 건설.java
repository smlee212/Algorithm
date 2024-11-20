import java.util.*;

class Solution {
    public int solution(int[][] board) {    
        
        int[][] d = {{0,1,0}, {1,0,1}, {0,-1,2}, {-1,0,3}}; // y, x, v
        int[][][] dp = new int[board.length][board.length][4];
        Deque<int[]> dq = new ArrayDeque<>(); // y, x, v, sum
        
        dq.add(new int[]{0,0,0,0});
        dq.add(new int[]{0,0,1,0});
        
        while(!dq.isEmpty()) {
            int[] now = dq.poll();
            int y = now[0];
            int x = now[1];
            int v = now[2];
            int sum = now[3];
            // System.out.println(y+", "+x+" -> "+v+" : "+sum);
            
            for(int i=0;i<4;i++) {
                int ny = y + d[i][0];
                int nx = x + d[i][1];
                int nv = d[i][2];
                int nsum = sum + 1;
                
                if(ny < 0 || nx < 0 || ny >= board.length || nx >= board.length || board[ny][nx] == 1) continue;
                
                if((v + nv) % 2 == 1) {
                    nsum += 5;
                }
                
                if(dp[ny][nx][nv] == 0 || nsum < dp[ny][nx][nv]) {
                    dp[ny][nx][nv] = nsum;
                    dq.add(new int[]{ny,nx,nv,nsum});
                }
            }
        }
        
        int minCost = Integer.MAX_VALUE;
        for(int i=0;i<4;i++) {
            if(dp[board.length-1][board.length-1][i] > 0)
                minCost = Math.min(minCost, dp[board.length-1][board.length-1][i]);
        }
        
        return minCost * 100;
    }
}