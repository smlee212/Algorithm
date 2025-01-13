import java.util.*;

class Solution {
    
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int K = skill.length;
        
        int[][] prefixSum = new int[N+1][M+1];
        
        for(int i=0;i<K;i++) {
            int type = skill[i][0];
            int r1 = skill[i][1], c1 = skill[i][2];
            int r2 = skill[i][3], c2 = skill[i][4];
            int degree = skill[i][5] * (type == 1 ? -1 : 1);
            
            prefixSum[r1][c1] += degree;
            prefixSum[r1][c2+1] += -degree;
            prefixSum[r2+1][c1] += -degree;
            prefixSum[r2+1][c2+1] += degree;
        }
        
        int cnt = 0;
        for(int i=0;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                prefixSum[i][j] += prefixSum[i][j-1];
            }
        }
        for(int i=1;i<=N;i++) {
            for(int j=0;j<=M;j++) {
                prefixSum[i][j] += prefixSum[i-1][j];
            }
        }
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(board[i][j] + prefixSum[i][j] > 0) cnt++;
            }
        }
        
        return cnt;
    }
}