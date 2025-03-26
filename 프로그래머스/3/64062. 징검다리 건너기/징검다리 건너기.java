import java.util.*;
class Solution { // 1시 35분 시작
    public int solution(int[] stones, int k) {        
        int result = 0;
        
        int l = 0, r = -1;
        for(int stone : stones) {
            r = Math.max(r, stone);
        }
        
        while(l<=r) {
            int m = (l+r) / 2;
            
            int cnt = 0;
            int maxCnt = -1;
            for(int stone : stones) {
                if(stone > m) {
                    cnt = 0;
                }
                else {
                    cnt++;
                    maxCnt = Math.max(maxCnt, cnt);
                }
            }
            
            if(maxCnt >= k) {
                r = m-1;
            }
            else {
                l = m+1;
            }
        }        
        return l;
    }
}