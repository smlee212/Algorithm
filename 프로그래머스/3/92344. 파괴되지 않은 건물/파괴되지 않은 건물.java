import java.util.*;

class Solution {
    
    // 누적합 문제
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int N = board.length;
        int M = board[0].length;
        int[][] sumMap = new int[N+1][M+1];
        
        for(int i=0;i<skill.length;i++) {
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5] * (skill[i][0] == 1 ? -1 : 1);
            
            sumMap[r1][c1] += degree;
            sumMap[r2+1][c2+1] += degree;
            sumMap[r2+1][c1] -= degree;
            sumMap[r1][c2+1] -= degree;
        }
        
        for(int i=0;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                sumMap[i][j] += sumMap[i][j-1];
            }
        }
        for(int i=1;i<=N;i++) {
            for(int j=0;j<=M;j++) {
                sumMap[i][j] += sumMap[i-1][j];
            }
        }
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                board[i][j] += sumMap[i][j];
                if(board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}