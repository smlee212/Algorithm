import java.util.*;

class Solution {
    public int solution(int [][]board) {
        int n = board.length;
        int m = board[0].length;
        int[][] map = new int[n][m];
        
        int maxLen = 0;
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(i==0 || j==0) {
                    map[i][j] = board[i][j];
                }
                else if(board[i][j] == 1) {
                    int minLen = Math.min(map[i-1][j-1], Math.min(map[i][j-1], map[i-1][j]));
                    map[i][j] = minLen + 1;
                    maxLen = Math.max(map[i][j], maxLen);
                }
                maxLen = Math.max(maxLen, map[i][j]);
            }
        }

        return maxLen * maxLen;
    }
}