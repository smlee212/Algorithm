import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        // 진출 시간을 기준으로 오름차순 정렬
        Arrays.sort(routes, (o1,o2) -> {
            return o1[1] - o2[1];
        });
        
        int lastTime = Integer.MIN_VALUE;
        int cnt = 0;
        for(int[] route : routes) {
            if(lastTime < route[0]) {
                lastTime = route[1];
                cnt++;
            }
        }
        
        return cnt;
    }
}