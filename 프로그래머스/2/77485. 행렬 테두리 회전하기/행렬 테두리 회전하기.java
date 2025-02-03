import java.util.*;

class Solution {
    
    int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] result = new int[queries.length];
        
        map = new int[rows+1][columns+1];
        int num = 1;
        for(int i=1;i<=rows;i++) {
            for(int j=1;j<=columns;j++) {
                map[i][j] = num++;
            }
        }
        
        for(int i=0;i<queries.length;i++) {
            int minNum = func(queries[i]);
            result[i] = minNum;
        }
        
        return result;
    }
    
    private int func(int[] query) {
        int x1 = query[0];
        int y1 = query[1];
        int x2 = query[2];
        int y2 = query[3];
        
        int minNum = Integer.MAX_VALUE;        
        
        // x1,y1 -> x1,y2
        int prev = map[x1+1][y1];   
        for(int j=y1;j<y2;j++) {
            int now = map[x1][j];
            map[x1][j] = prev;
            prev = now;
            minNum = Math.min(minNum, now);
        }
        
        // x1,y2 -> x2,y2
        for(int i=x1;i<x2;i++) {
            int now = map[i][y2];
            map[i][y2] = prev;
            prev = now;
            minNum = Math.min(minNum, now);
        }        
        
        // x2,y2 -> x2,y1
        for(int j=y2;j>y1;j--) {
            int now = map[x2][j];
            map[x2][j] = prev;
            prev = now;
            minNum = Math.min(minNum, now);
        }        
        
        // x2,y1 -> x1,y1
        for(int i=x2;i>x1;i--) {
            int now = map[i][y1];
            map[i][y1] = prev;
            prev = now;
            minNum = Math.min(minNum, now);
        }

        return minNum;
    }
    
}