import java.util.*;

class Solution {
    
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        
        // key의 돌기와 lock의 홈 개수 구하기
        int lock0Cnt = 0;
        int key1Cnt = 0;
        for(int[] a : lock) {
            for(int b : a) {
                if(b == 0) lock0Cnt++;
            }
        }
        for(int[] a : key) {
            for(int b : a) {
                if(b == 1) key1Cnt++;
            }
        }
        // 홈 개수가 돌기 개수보다 많으면 열수가 없음
        if(lock0Cnt > key1Cnt) return false;
        else if(lock0Cnt == 0) return true;
        
        // 회전했을때의 key 배열들
        int[][][] rotateKeys = new int[4][key.length][key.length];
        int[][] temp = copyKey(key);
        for(int i=0;i<4;i++) {
            rotateKeys[i] = temp;
            temp = rotate90Key(temp);
        }
        
        // 모든 범위에 대해 비교
        boolean isCorrect = false;
        for(int i=1-M;i<N;i++) {
            for(int j=1-M;j<N;j++) {
                for(int k=0;k<4;k++) {
                    if(func(rotateKeys[k], lock, i, j, lock0Cnt)) return true;
                }
            }
        }        
        
        return false;
    }
    
    int[][] copyKey(int[][] key) {
        int[][] resultKey = Arrays.copyOf(key, key.length);
        for(int i=0;i<key.length;i++) {
            resultKey[i] = Arrays.copyOf(key[i], key[i].length);
        }
        return resultKey;
    }
    
    int[][] rotate90Key(int[][] key) {
        int[][] resultKey = new int[key.length][key.length];
        
        for(int i=0;i<key.length;i++) {
            for(int j=0;j<key.length;j++) {
                resultKey[j][key.length-1-i] = key[i][j];
            }
        }
        
        return resultKey;
    }
    
    boolean func(int[][] key, int[][] lock, int y, int x, int lock0Cnt) {
        int correctkeyAndLock = 0;
        for(int i=0;i<key.length;i++) {
            for(int j=0;j<key.length;j++) {
                int ny = y + i;
                int nx = x + j;
                
                if(ny<0||nx<0||ny>=lock.length||nx>=lock.length) continue;
                
                if(lock[ny][nx] == 0 && key[i][j] == 1) {
                    correctkeyAndLock++;
                }
                else if(lock[ny][nx] == key[i][j]) {
                    return false;
                }
            }
        }
        
        return correctkeyAndLock == lock0Cnt ? true : false;
    }
}